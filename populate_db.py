import requests
from faker import Faker
import random
import json
import os
import time
import sys

# Force UTF-8 encoding for stdout/stderr to handle special characters if possible,
# otherwise we will use safe ASCII characters.
sys.stdout.reconfigure(encoding='utf-8')
sys.stderr.reconfigure(encoding='utf-8')

# Initialize Faker
fake = Faker()

# Configuration
BASE_URL = "http://localhost:9000"
API_BASE = f"{BASE_URL}/api"
LIBRARY_BASE = f"{BASE_URL}/library"
MODELS_BASE = f"{BASE_URL}/models"

# Geology Domain Data
GEOLOGY_TERMS = [
    "Stratigraphy", "Petrology", "Mineralogy", "Paleontology", "Volcanology",
    "Seismology", "Geophysics", "Hydrogeology", "Tectonics", "Geochemistry",
    "Sedimentology", "Geomorphology", "Crystallography", "Gemology"
]

ROCK_TYPES = ["Igneous", "Sedimentary", "Metamorphic"]
ROCK_NAMES = ["Granite", "Basalt", "Limestone", "Sandstone", "Marble", "Slate", "Gneiss", "Obsidian", "Pumice", "Shale"]
MINERAL_NAMES = ["Quartz", "Feldspar", "Mica", "Calcite", "Pyrite", "Hematite", "Magnetite", "Gypsum", "Talc", "Diamond"]
FOSSIL_NAMES = ["Trilobite", "Ammonite", "Brachiopod", "Fern", "Dinosaur Bone", "Shark Tooth", "Amber", "Petrified Wood"]

# Global Storage for Dependencies
STORE = {
    "formation_names": [],
    "branch_names": [],
    "grade_names": [],
    "teacher_emails": [],
    "student_emails": [],
    "validation_code": None,
    "classroom_ids": [],
    "subject_ids": [],
    "library_category_ids": [],
    "event_category_ids": [],
    "activity_sector_ids": [],
    "internship_category_ids": [],
    "collection_subcategories": [] # Not explicitly used in API but good to track if needed
}

# --- Helper Functions ---

def create_dummy_files():
    """Creates dummy files for upload testing."""
    files = {
        "image.jpg": os.urandom(1024),
        "video.mp4": os.urandom(2048),
        "model.glb": os.urandom(2048),
        "doc.pdf": os.urandom(1024)
    }
    for name, content in files.items():
        if not os.path.exists(name):
            with open(name, "wb") as f:
                f.write(content)
    return list(files.keys())

def cleanup_files(files):
    """Removes dummy files."""
    for f in files:
        if os.path.exists(f):
            os.remove(f)

def get_random_geology_title():
    return f"{random.choice(GEOLOGY_TERMS)}: {fake.sentence(nb_words=4)}"

def get_random_geology_desc():
    return f"A study regarding {random.choice(GEOLOGY_TERMS)}. {fake.paragraph(nb_sentences=3)}"

def log(msg, success=True):
    # Using ASCII characters to avoid UnicodeEncodeError on Windows consoles
    icon = "[OK]" if success else "[FAIL]"
    print(f"{icon} {msg}")

# --- Population Functions ---

def populate_formations_and_branches():
    print("\n--- Populating Formations & Branches ---")
    formations = ["Geological Engineering", "Mining Engineering", "Geophysics", "Hydrogeology"]
    
    for fname in formations:
        # Create Formation
        resp = requests.post(f"{API_BASE}/formations", json={"name": fname})
        if resp.status_code == 201:
            log(f"Created Formation: {fname}")
            STORE["formation_names"].append(fname)
            
            # Get ID (Need to fetch all to find ID, or just use name if API supports it. 
            # The API for adding branch takes ID: /formations/{id}/branches)
            # Let's fetch all formations to get the ID of the one we just created
            all_forms = requests.get(f"{API_BASE}/formations").json()
            f_id = next((f['id'] for f in all_forms if f['name'] == fname), None)
            
            if f_id:
                # Create Branches
                for _ in range(2):
                    bname = f"{fname} - {fake.word().capitalize()} Specialization"
                    b_resp = requests.post(f"{API_BASE}/formations/{f_id}/branches", json={"name": bname})
                    if b_resp.status_code == 201:
                        log(f"  Created Branch: {bname}")
                        STORE["branch_names"].append(bname)
                    else:
                        log(f"  Failed Branch {bname}: {b_resp.status_code}", False)
        else:
            log(f"Failed Formation {fname}: {resp.status_code}", False)

def populate_grades_and_teachers():
    print("\n--- Populating Grades & Teachers ---")
    grades = ["Professor", "Associate Professor", "Assistant Professor", "Lecturer"]
    
    for gname in grades:
        # Create Grade
        resp = requests.post(f"{API_BASE}/grades", json={"name": gname})
        if resp.status_code == 201:
            log(f"Created Grade: {gname}")
            STORE["grade_names"].append(gname)
            
            # Create Teachers
            for _ in range(3):
                email = fake.email()
                teacher = {
                    "firstName": fake.first_name(),
                    "lastName": fake.last_name(),
                    "email": email,
                    "password": "password123",
                    "phoneNumber": fake.phone_number(),
                    "birthDay": fake.date_of_birth(minimum_age=30, maximum_age=60).isoformat(),
                    "identityCardNumber": fake.ssn()
                }
                t_resp = requests.post(f"{API_BASE}/grades/{gname}/teachers", json=teacher)
                if t_resp.status_code == 201:
                    log(f"  Created Teacher: {email}")
                    STORE["teacher_emails"].append(email)
                    
                    # Validate Teacher (Optional, but good for realism)
                    # We need the ID. Fetch teachers.
                    # Assuming we can filter or just get latest.
                    # For simplicity, we skip explicit validation call unless we fetch IDs.
                else:
                    log(f"  Failed Teacher {email}: {t_resp.status_code} {t_resp.text}", False)
        else:
            log(f"Failed Grade {gname}: {resp.status_code}", False)

def generate_validation_code():
    print("\n--- Generating Validation Code ---")
    # Generate a code valid for 1 hour
    resp = requests.get(f"{API_BASE}/teachers/generate-validation-code/3600")
    if resp.status_code == 200:
        code = resp.text
        STORE["validation_code"] = code
        log(f"Generated Code: {code}")
    else:
        log(f"Failed to generate code: {resp.status_code}", False)

def populate_students():
    print("\n--- Populating Students ---")
    if not STORE["branch_names"] or not STORE["validation_code"]:
        log("Skipping students (missing branches or code)", False)
        return

    for _ in range(10):
        branch = random.choice(STORE["branch_names"])
        email = fake.email()
        student = {
            "firstName": fake.first_name(),
            "lastName": fake.last_name(),
            "email": email,
            "password": "password123",
            "phoneNumber": fake.phone_number(),
            "birthDay": fake.date_of_birth(minimum_age=18, maximum_age=25).isoformat(),
            "identityCardNumber": fake.ssn(),
            "anneeScolaire": random.randint(1, 5),
            "semisterNumber": random.randint(1, 2)
        }
        
        url = f"{API_BASE}/branches/{branch}/students/{STORE['validation_code']}"
        resp = requests.post(url, json=student)
        if resp.status_code == 201:
            log(f"Created Student: {email} in {branch}")
            STORE["student_emails"].append(email)
        else:
            log(f"Failed Student {email}: {resp.status_code} {resp.text}", False)

def populate_classrooms_subjects_materials():
    print("\n--- Populating Classrooms, Subjects & Materials ---")
    if not STORE["teacher_emails"]:
        log("Skipping classrooms (no teachers)", False)
        return

    for _ in range(5):
        teacher_email = random.choice(STORE["teacher_emails"])
        title = f"{random.choice(GEOLOGY_TERMS)} 101"
        
        # Create Classroom (Multipart)
        classroom_dto = {
            "title": title,
            "teacherEmail": teacher_email
        }
        
        files = {
            'class': (None, json.dumps(classroom_dto), 'application/json'),
            'image': ('image.jpg', open('image.jpg', 'rb'), 'image/jpeg')
        }
        
        resp = requests.post(f"{API_BASE}/classrooms", files=files)
        if resp.status_code == 201:
            log(f"Created Classroom: {title} by {teacher_email}")
            
            # We need the Classroom ID to add subjects. 
            # Fetch teacher's classrooms to find it.
            c_resp = requests.get(f"{API_BASE}/classrooms/teacher/{teacher_email}")
            if c_resp.status_code == 200:
                classrooms = c_resp.json()
                # Assuming the last one is the one we just created, or filter by title
                # Since titles might duplicate, let's just pick one from the list to add subjects to
                if classrooms:
                    c_id = classrooms[0]['id']
                    STORE["classroom_ids"].append(c_id)
                    
                    # Add Subjects
                    for _ in range(2):
                        sub_name = f"Intro to {random.choice(GEOLOGY_TERMS)}"
                        s_resp = requests.post(f"{API_BASE}/classrooms/{c_id}/subjects", json={"name": sub_name})
                        if s_resp.status_code == 201:
                            log(f"  Added Subject: {sub_name}")
                            
                            # Need Subject ID to add materials.
                            # Fetch subjects
                            sub_resp = requests.get(f"{API_BASE}/classrooms/{c_id}/subjects")
                            if sub_resp.status_code == 200:
                                subjects = sub_resp.json()
                                # Find the subject we just added (or any)
                                target_sub = next((s for s in subjects if s['name'] == sub_name), None)
                                if target_sub:
                                    sub_id = target_sub['id']
                                    
                                    # Add Material
                                    mat_dto = {
                                        "title": f"Lecture Notes: {sub_name}",
                                        "description": "PDF slides for the lecture",
                                        "type": "PDF"
                                    }
                                    m_files = {
                                        'material': (None, json.dumps(mat_dto), 'application/json'),
                                        'file': ('doc.pdf', open('doc.pdf', 'rb'), 'application/pdf')
                                    }
                                    m_resp = requests.post(f"{API_BASE}/classrooms/subjects/{sub_id}/materials", files=m_files)
                                    if m_resp.status_code == 201:
                                        log(f"    Added Material to {sub_name}")
                                    else:
                                        log(f"    Failed Material: {m_resp.status_code}", False)
        else:
            log(f"Failed Classroom {title}: {resp.status_code}", False)

def populate_library():
    print("\n--- Populating Library ---")
    categories = ["Journals", "Field Guides", "Maps", "Thesis Archives"]
    
    for cat in categories:
        resp = requests.post(f"{LIBRARY_BASE}/categories", json={"name": cat})
        if resp.status_code == 201:
            log(f"Created Library Category: {cat}")
            
            # Get ID
            all_cats = requests.get(f"{LIBRARY_BASE}/categories").json()
            cat_id = next((c['id'] for c in all_cats if c['name'] == cat), None)
            
            if cat_id:
                STORE["library_category_ids"].append(cat_id)
                
                # Add Articles
                for _ in range(3):
                    article = {
                        "title": get_random_geology_title(),
                        "descreption": get_random_geology_desc(),
                        "videoUrl": "http://example.com/video"
                    }
                    files = {
                        'article': (None, json.dumps(article), 'application/json'),
                        'cover': ('image.jpg', open('image.jpg', 'rb'), 'image/jpeg'),
                        'data': ('doc.pdf', open('doc.pdf', 'rb'), 'application/pdf')
                    }
                    a_resp = requests.post(f"{LIBRARY_BASE}/{cat_id}/articles", files=files)
                    if a_resp.status_code == 201:
                        log(f"  Added Article: {article['title']}")
                    else:
                        log(f"  Failed Article: {a_resp.status_code}", False)

def populate_event_internship_metadata():
    print("\n--- Populating Event/Internship Metadata ---")
    
    # Event Categories
    e_cats = ["Conference", "Workshop", "Field Trip", "Seminar"]
    for c in e_cats:
        resp = requests.post(f"{API_BASE}/eventCategory/", json={"label": c})
        if resp.status_code == 201:
            log(f"Event Category: {c}")
    
    # Fetch IDs
    try:
        STORE["event_category_ids"] = [x['id'] for x in requests.get(f"{API_BASE}/eventCategory/").json()]
    except: pass

    # Activity Sectors
    sectors = ["Oil & Gas", "Mining", "Research", "Environmental Consulting"]
    for s in sectors:
        resp = requests.post(f"{API_BASE}/sector/", json={"label": s})
        if resp.status_code == 201:
            log(f"Sector: {s}")
            
    # Fetch IDs
    try:
        STORE["activity_sector_ids"] = [x['id'] for x in requests.get(f"{API_BASE}/sector/").json()]
    except: pass

    # Internship Categories
    i_cats = ["Summer Internship", "End of Studies Project", "Professional Training"]
    for c in i_cats:
        resp = requests.post(f"{API_BASE}/internshipCategory/", json={"label": c})
        if resp.status_code == 201:
            log(f"Internship Category: {c}")

    # Fetch IDs
    try:
        STORE["internship_category_ids"] = [x['id'] for x in requests.get(f"{API_BASE}/internshipCategory/").json()]
    except: pass
    
    # Entreprises
    entreprises = ["GeoTech Solutions", "National Mining Co", "EcoEarth Consult", "PetroFinders"]
    for e in entreprises:
        resp = requests.post(f"{API_BASE}/entreprise/", json={"name": e, "url": f"http://{e.lower().replace(' ', '')}.com"})
        if resp.status_code == 201:
            log(f"Entreprise: {e}")

def populate_events_internships_jobs():
    print("\n--- Populating Events, Internships & Jobs ---")
    
    # Events
    if STORE["event_category_ids"]:
        for _ in range(5):
            event_dto = {
                "location": fake.city(),
                "date": fake.future_date().isoformat(),
                "title": f"International {random.choice(GEOLOGY_TERMS)} Conference",
                "description": fake.paragraph(),
                "summary": fake.sentence(),
                "categoryId": random.choice(STORE["event_category_ids"])
            }
            files = {
                'event': (None, json.dumps(event_dto), 'application/json'),
                'image': ('image.jpg', open('image.jpg', 'rb'), 'image/jpeg')
            }
            resp = requests.post(f"{API_BASE}/event/", files=files)
            if resp.status_code == 201:
                log(f"Created Event: {event_dto['title']}")
            else:
                log(f"Failed Event: {resp.status_code}", False)

    # Internships
    if STORE["internship_category_ids"] and STORE["activity_sector_ids"]:
        for _ in range(5):
            internship = {
                "title": f"Junior {random.choice(GEOLOGY_TERMS)} Intern",
                "description": fake.paragraph(),
                "remuneration": random.randint(500, 2000),
                "startDate": fake.date_this_year().isoformat(),
                "endDate": fake.future_date().isoformat(),
                "recruiter": fake.name(),
                "recruiterPhoneNumber": fake.phone_number(),
                "recruiterEmail": fake.email(),
                "country": fake.country(),
                "city": fake.city(),
                "isRemote": random.choice([True, False]),
                "categoryId": random.choice(STORE["internship_category_ids"]),
                "sectorId": random.choice(STORE["activity_sector_ids"])
            }
            resp = requests.post(f"{API_BASE}/internship/", json=internship)
            if resp.status_code == 201:
                log(f"Created Internship: {internship['title']}")
            else:
                log(f"Failed Internship: {resp.status_code}", False)

    # Jobs
    if STORE["internship_category_ids"] and STORE["activity_sector_ids"]:
        for _ in range(5):
            job = {
                "title": f"Senior {random.choice(GEOLOGY_TERMS)} Specialist",
                "description": fake.paragraph(),
                "recruiter": fake.name(),
                "recruiterPhoneNumber": fake.phone_number(),
                "recruiterEmail": fake.email(),
                "country": fake.country(),
                "city": fake.city(),
                "contractType": random.choice(["CDI", "CDD"]),
                "requiredExperienceDurationInMonths": random.randint(12, 60),
                "categoryId": random.choice(STORE["internship_category_ids"]), # Assuming same categories
                "sectorId": random.choice(STORE["activity_sector_ids"]),
                "remote": random.choice([True, False])
            }
            resp = requests.post(f"{API_BASE}/job/", json=job)
            if resp.status_code == 201:
                log(f"Created Job: {job['title']}")
            else:
                log(f"Failed Job: {resp.status_code}", False)

def populate_study_announcements_and_theses():
    print("\n--- Populating Study Announcements & Theses ---")
    
    # Announcements
    levels = ["LICENCE", "MASTER", "DOCTORAT"]
    for _ in range(5):
        ann = {
            "title": f"Scholarship for {random.choice(GEOLOGY_TERMS)}",
            "description": fake.paragraph(),
            "level": random.choice(levels)
        }
        resp = requests.post(f"{API_BASE}/studyAnnouncement/", json=ann)
        if resp.status_code == 201:
            log(f"Created Announcement: {ann['title']}")

    # Theses
    for _ in range(5):
        thesis = {
            "subject": f"Analysis of {random.choice(ROCK_NAMES)} formations in {fake.country()}",
            "description": fake.paragraph(),
            "startDate": fake.date_this_year().isoformat(),
            "endDate": fake.future_date().isoformat(),
            "level": random.choice(levels),
            "status": "AVAILABLE",
            "supervisorsIds": [] # Can be populated if we fetch teacher IDs
        }
        resp = requests.post(f"{API_BASE}/thesis/", json=thesis)
        if resp.status_code == 201:
            log(f"Created Thesis: {thesis['subject']}")
        else:
            log(f"Failed Thesis: {resp.status_code}", False)

def populate_collections():
    print("\n--- Populating 3D Collections ---")
    
    # Rocks
    for _ in range(3):
        rock_name = random.choice(ROCK_NAMES)
        rock_model = {
            "inventoryNumber": f"R-{random.randint(1000, 9999)}",
            "subcategoryId": 1, # Dummy ID, assuming some exist or optional
            "otherInfos": [{"propertyName": "Location", "propertyValue": fake.country()}],
            "rockProperties": {
                "name": rock_name,
                "rockType": random.choice(ROCK_TYPES),
                "color": fake.color_name(),
                "texture": "Granular",
                "primaryMinerals": "Quartz, Feldspar",
                "uses": "Construction",
                "parentRock": "N/A",
                "metamorphismType": "N/A"
            }
        }
        files = {
            'model': (None, json.dumps(rock_model), 'application/json'),
            'model3d': ('model.glb', open('model.glb', 'rb'), 'application/octet-stream'),
            'video': ('video.mp4', open('video.mp4', 'rb'), 'video/mp4'),
            'images': ('image.jpg', open('image.jpg', 'rb'), 'image/jpeg')
        }
        # Note: 'images' is a List<MultipartFile>, requests handles list of tuples for multiple files with same key
        # But here we just send one for simplicity. To send multiple:
        # files = [ ... ('images', (...)), ('images', (...)) ]
        
        resp = requests.post(f"{MODELS_BASE}/rocks", files=files)
        if resp.status_code == 201:
            log(f"Created Rock Model: {rock_name}")
        else:
            log(f"Failed Rock Model: {resp.status_code}", False)

    # Minerals
    for _ in range(3):
        min_name = random.choice(MINERAL_NAMES)
        min_model = {
            "inventoryNumber": f"M-{random.randint(1000, 9999)}",
            "subcategoryId": 1,
            "otherInfos": [],
            "mineralProperties": {
                "name": min_name,
                "chemicalFormula": "SiO2",
                "mineralClass": "Silicate",
                "crystalSystem": "Hexagonal",
                "color": fake.color_name(),
                "eclat": "Vitreous",
                "durete": 7,
                "clivage": "None",
                "cassure": "Conchoidal",
                "density": 2.65,
                "transparency": "Transparent",
                "uses": "Jewelry, Electronics"
            }
        }
        files = {
            'model': (None, json.dumps(min_model), 'application/json'),
            'model3d': ('model.glb', open('model.glb', 'rb'), 'application/octet-stream'),
            'video': ('video.mp4', open('video.mp4', 'rb'), 'video/mp4'),
            'images': ('image.jpg', open('image.jpg', 'rb'), 'image/jpeg')
        }
        resp = requests.post(f"{MODELS_BASE}/minerals", files=files)
        if resp.status_code == 201:
            log(f"Created Mineral Model: {min_name}")
        else:
            log(f"Failed Mineral Model: {resp.status_code}", False)

    # Fossils
    for _ in range(3):
        fos_name = random.choice(FOSSIL_NAMES)
        fos_model = {
            "inventoryNumber": f"F-{random.randint(1000, 9999)}",
            "subcategoryId": 1,
            "otherInfos": [],
            "fossilProperties": {
                "scientificName": fos_name,
                "age": "Jurassic",
                "discoveryLocation": fake.country(),
                "gpsCoordinates": f"{fake.latitude()}, {fake.longitude()}",
                "fossilType": "Body Fossil",
                "specimenType": "Original"
            }
        }
        files = {
            'model': (None, json.dumps(fos_model), 'application/json'),
            'model3d': ('model.glb', open('model.glb', 'rb'), 'application/octet-stream'),
            'video': ('video.mp4', open('video.mp4', 'rb'), 'video/mp4'),
            'images': ('image.jpg', open('image.jpg', 'rb'), 'image/jpeg')
        }
        resp = requests.post(f"{MODELS_BASE}/fossils", files=files)
        if resp.status_code == 201:
            log(f"Created Fossil Model: {fos_name}")
        else:
            log(f"Failed Fossil Model: {resp.status_code}", False)

# --- Main Execution ---

if __name__ == "__main__":
    print("Starting Geology Platform Database Population...")
    
    dummy_files = create_dummy_files()
    
    try:
        populate_formations_and_branches()
        populate_grades_and_teachers()
        generate_validation_code()
        populate_students()
        populate_classrooms_subjects_materials()
        populate_library()
        populate_event_internship_metadata()
        populate_events_internships_jobs()
        populate_study_announcements_and_theses()
        populate_collections()
        
    except Exception as e:
        print(f"\n[ERROR] An unexpected error occurred: {e}")
    finally:
        print("\nCleaning up dummy files...")
        cleanup_files(dummy_files)
        print("Done.")
