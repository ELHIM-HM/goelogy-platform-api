import requests
from faker import Faker
import random
import json
import os

fake = Faker()
BASE_URL = "http://localhost:9000/api"

# Helper function to create a dummy file for upload
def create_dummy_file(filename="test.txt", content="dummy content"):
    with open(filename, "w") as f:
        f.write(content)
    return filename

def create_dummy_image(filename="test.jpg"):
    # Create a simple dummy image file (not a real image, just bytes)
    with open(filename, "wb") as f:
        f.write(os.urandom(1024))
    return filename

def populate_admin_authorities():
    print("Populating Admin Authorities...")
    for _ in range(5):
        authority = {
            "authority": fake.job().upper().replace(" ", "_")
        }
        try:
            response = requests.post(f"{BASE_URL}/authorities", json=authority)
            if response.status_code == 201:
                print(f"Created authority: {authority['authority']}")
            else:
                print(f"Failed to create authority: {response.status_code}")
        except Exception as e:
            print(f"Error creating authority: {e}")

def populate_student_formations_and_branches():
    print("Populating Formations and Branches...")
    formation_ids = []
    formation_names = []
    
    # Create Formations
    for _ in range(5):
        formation_name = fake.word().capitalize() + " Science"
        formation = {"name": formation_name}
        try:
            response = requests.post(f"{BASE_URL}/formations", json=formation)
            if response.status_code == 201:
                print(f"Created formation: {formation_name}")
                formation_names.append(formation_name)
                # We need to fetch formations to get IDs, but for now we can use names for branches
            else:
                print(f"Failed to create formation: {response.status_code}")
        except Exception as e:
            print(f"Error creating formation: {e}")

    # Fetch formations to get IDs
    try:
        response = requests.get(f"{BASE_URL}/formations")
        if response.status_code == 200:
            formations = response.json()
            for f in formations:
                formation_ids.append(f['id'])
    except Exception as e:
        print(f"Error fetching formations: {e}")

    # Create Branches
    for f_id in formation_ids:
        for _ in range(3):
            branch = {"name": fake.word().capitalize() + " Branch"}
            try:
                response = requests.post(f"{BASE_URL}/formations/{f_id}/branches", json=branch)
                if response.status_code == 201:
                    print(f"Created branch for formation {f_id}")
                else:
                    print(f"Failed to create branch: {response.status_code}")
            except Exception as e:
                print(f"Error creating branch: {e}")

def populate_teacher_grades_and_teachers():
    print("Populating Grades and Teachers...")
    grade_names = []
    
    # Create Grades
    for _ in range(3):
        grade_name = fake.job()
        grade = {"name": grade_name}
        try:
            response = requests.post(f"{BASE_URL}/grades", json=grade)
            if response.status_code == 201:
                print(f"Created grade: {grade_name}")
                grade_names.append(grade_name)
            else:
                print(f"Failed to create grade: {response.status_code}")
        except Exception as e:
            print(f"Error creating grade: {e}")

    # Create Teachers
    for grade_name in grade_names:
        for _ in range(5):
            teacher = {
                "firstName": fake.first_name(),
                "lastName": fake.last_name(),
                "email": fake.email(),
                "password": "password123",
                "phoneNumber": fake.phone_number(),
                "birthDay": fake.date(),
                "identityCardNumber": fake.ssn()
            }
            try:
                response = requests.post(f"{BASE_URL}/grades/{grade_name}/teachers", json=teacher)
                if response.status_code == 201:
                    print(f"Created teacher: {teacher['email']}")
                else:
                    print(f"Failed to create teacher: {response.status_code} - {response.text}")
            except Exception as e:
                print(f"Error creating teacher: {e}")

def populate_library_categories_and_articles():
    print("Populating Library Categories and Articles...")
    category_ids = []
    
    # Create Categories
    for _ in range(5):
        category = {"name": fake.word().capitalize()}
        try:
            response = requests.post("http://localhost:9000/library/categories", json=category)
            if response.status_code == 201:
                print(f"Created library category: {category['name']}")
            else:
                print(f"Failed to create library category: {response.status_code}")
        except Exception as e:
            print(f"Error creating library category: {e}")

    # Fetch categories
    try:
        response = requests.get("http://localhost:9000/library/categories")
        if response.status_code == 200:
            categories = response.json()
            for c in categories:
                category_ids.append(c['id'])
    except Exception as e:
        print(f"Error fetching library categories: {e}")

    # Create Articles
    dummy_image = create_dummy_image()
    dummy_file = create_dummy_file()
    
    for cat_id in category_ids:
        for _ in range(5):
            article = {
                "title": fake.sentence(),
                "descreption": fake.paragraph(),
                "videoUrl": fake.url()
            }
            
            files = {
                'article': (None, json.dumps(article), 'application/json'),
                'cover': ('cover.jpg', open(dummy_image, 'rb'), 'image/jpeg'),
                'data': ('data.txt', open(dummy_file, 'rb'), 'text/plain')
            }
            
            try:
                response = requests.post(f"http://localhost:9000/library/{cat_id}/articles", files=files)
                if response.status_code == 201:
                    print(f"Created article in category {cat_id}")
                else:
                    print(f"Failed to create article: {response.status_code}")
            except Exception as e:
                print(f"Error creating article: {e}")
            finally:
                # Re-open files for next iteration if needed, or just let them close
                pass

    # Clean up dummy files
    try:
        os.remove(dummy_image)
        os.remove(dummy_file)
    except:
        pass

def populate_event_categories():
    print("Populating Event Categories...")
    for _ in range(5):
        category = {"label": fake.word().capitalize()}
        try:
            response = requests.post(f"{BASE_URL}/eventCategory/", json=category)
            if response.status_code == 201:
                print(f"Created event category: {category['label']}")
            else:
                print(f"Failed to create event category: {response.status_code}")
        except Exception as e:
            print(f"Error creating event category: {e}")

def populate_activity_sectors():
    print("Populating Activity Sectors...")
    for _ in range(5):
        sector = {"label": fake.job()}
        try:
            response = requests.post(f"{BASE_URL}/sector/", json=sector)
            if response.status_code == 201:
                print(f"Created activity sector: {sector['label']}")
            else:
                print(f"Failed to create activity sector: {response.status_code}")
        except Exception as e:
            print(f"Error creating activity sector: {e}")

def populate_internship_categories():
    print("Populating Internship Categories...")
    for _ in range(5):
        category = {"label": fake.bs()}
        try:
            response = requests.post(f"{BASE_URL}/internshipCategory/", json=category)
            if response.status_code == 201:
                print(f"Created internship category: {category['label']}")
            else:
                print(f"Failed to create internship category: {response.status_code}")
        except Exception as e:
            print(f"Error creating internship category: {e}")

def populate_entreprises():
    print("Populating Entreprises...")
    for _ in range(10):
        entreprise = {
            "name": fake.company(),
            "url": fake.url()
        }
        try:
            response = requests.post(f"{BASE_URL}/entreprise/", json=entreprise)
            if response.status_code == 201:
                print(f"Created entreprise: {entreprise['name']}")
            else:
                print(f"Failed to create entreprise: {response.status_code}")
        except Exception as e:
            print(f"Error creating entreprise: {e}")

def populate_study_announcements():
    print("Populating Study Announcements...")
    levels = ["LICENCE", "MASTER", "DOCTORAT"]
    for _ in range(10):
        announcement = {
            "title": fake.sentence(),
            "description": fake.paragraph(),
            "level": random.choice(levels)
        }
        try:
            response = requests.post(f"{BASE_URL}/studyAnnouncement/", json=announcement)
            if response.status_code == 201:
                print(f"Created study announcement: {announcement['title']}")
            else:
                print(f"Failed to create study announcement: {response.status_code}")
        except Exception as e:
            print(f"Error creating study announcement: {e}")

def populate_events():
    print("Populating Events...")
    # Get categories first
    category_ids = []
    try:
        response = requests.get(f"{BASE_URL}/eventCategory/")
        if response.status_code == 200:
            cats = response.json()
            category_ids = [c['id'] for c in cats]
    except:
        pass
    
    if not category_ids:
        print("No event categories found, skipping events.")
        return

    dummy_image = create_dummy_image()
    
    for _ in range(10):
        event_dto = {
            "location": fake.city(),
            "date": fake.date_this_year().isoformat(),
            "title": fake.sentence(),
            "description": fake.paragraph(),
            "summary": fake.sentence(),
            "categoryId": random.choice(category_ids)
        }
        
        files = {
            'event': (None, json.dumps(event_dto), 'application/json'),
            'image': ('image.jpg', open(dummy_image, 'rb'), 'image/jpeg')
        }
        
        try:
            response = requests.post(f"{BASE_URL}/event/", files=files)
            if response.status_code == 201:
                print(f"Created event: {event_dto['title']}")
            else:
                print(f"Failed to create event: {response.status_code}")
        except Exception as e:
            print(f"Error creating event: {e}")

    try:
        os.remove(dummy_image)
    except:
        pass

def populate_internships():
    print("Populating Internships...")
    # Get categories and sectors
    cat_ids = []
    sec_ids = []
    try:
        r1 = requests.get(f"{BASE_URL}/internshipCategory/")
        if r1.status_code == 200: cat_ids = [c['id'] for c in r1.json()]
        r2 = requests.get(f"{BASE_URL}/sector/")
        if r2.status_code == 200: sec_ids = [s['id'] for s in r2.json()]
    except:
        pass

    if not cat_ids or not sec_ids:
        print("Missing categories or sectors for internships")
        return

    for _ in range(10):
        internship = {
            "title": fake.job(),
            "description": fake.paragraph(),
            "remuneration": random.uniform(500, 2000),
            "startDate": fake.date_this_year().isoformat(),
            "endDate": fake.date_this_year().isoformat(),
            "recruiter": fake.name(),
            "recruiterPhoneNumber": fake.phone_number(),
            "recruiterEmail": fake.email(),
            "country": fake.country(),
            "city": fake.city(),
            "isRemote": random.choice([True, False]),
            "categoryId": random.choice(cat_ids),
            "sectorId": random.choice(sec_ids)
        }
        try:
            response = requests.post(f"{BASE_URL}/internship/", json=internship)
            if response.status_code == 201:
                print(f"Created internship: {internship['title']}")
            else:
                print(f"Failed to create internship: {response.status_code}")
        except Exception as e:
            print(f"Error creating internship: {e}")

def populate_jobs():
    print("Populating Jobs...")
    # Reuse categories and sectors from internships logic if applicable, or fetch again
    # Assuming JobController uses same IDs or similar logic
    # The JobDTO has categoryId and sectorId
    
    # We need valid IDs. Let's assume we can use the ones from internship categories/sectors or fetch if needed.
    # The JobController endpoints suggest it uses IDs.
    
    # Fetch sectors
    sec_ids = []
    try:
        r = requests.get(f"{BASE_URL}/sector/")
        if r.status_code == 200: sec_ids = [s['id'] for s in r.json()]
    except:
        pass
        
    # JobDTO has categoryId, but there isn't a specific JobCategoryController shown, 
    # maybe it uses InternshipCategory or EventCategory? 
    # Let's assume it uses InternshipCategory for now as they are often related, or just random ints if not strict.
    # Based on code, JobDTO has categoryId. Let's try to use InternshipCategory IDs.
    cat_ids = []
    try:
        r = requests.get(f"{BASE_URL}/internshipCategory/")
        if r.status_code == 200: cat_ids = [c['id'] for c in r.json()]
    except:
        pass

    if not sec_ids or not cat_ids:
        print("Missing sectors or categories for jobs")
        return

    for _ in range(10):
        job = {
            "title": fake.job(),
            "description": fake.paragraph(),
            "recruiter": fake.name(),
            "recruiterPhoneNumber": fake.phone_number(),
            "recruiterEmail": fake.email(),
            "country": fake.country(),
            "city": fake.city(),
            "contractType": random.choice(["CDI", "CDD", "Freelance"]),
            "requiredExperienceDurationInMonths": random.randint(0, 60),
            "categoryId": random.choice(cat_ids),
            "sectorId": random.choice(sec_ids),
            "remote": random.choice([True, False])
        }
        try:
            response = requests.post(f"{BASE_URL}/job/", json=job)
            if response.status_code == 201:
                print(f"Created job: {job['title']}")
            else:
                print(f"Failed to create job: {response.status_code}")
        except Exception as e:
            print(f"Error creating job: {e}")

def populate_theses():
    print("Populating Theses...")
    levels = ["LICENCE", "MASTER", "DOCTORAT"]
    statuses = ["AVAILABLE", "ASSIGNED"]
    
    for _ in range(10):
        thesis = {
            "subject": fake.sentence(),
            "description": fake.paragraph(),
            "startDate": fake.date_this_year().isoformat(),
            "endDate": fake.date_this_year().isoformat(),
            "level": random.choice(levels),
            "status": random.choice(statuses),
            "supervisorsIds": [] # Assuming no supervisors for now or need to fetch teachers
        }
        try:
            response = requests.post(f"{BASE_URL}/thesis/", json=thesis)
            if response.status_code == 201:
                print(f"Created thesis: {thesis['subject']}")
            else:
                print(f"Failed to create thesis: {response.status_code}")
        except Exception as e:
            print(f"Error creating thesis: {e}")

if __name__ == "__main__":
    print("Starting database population...")
    populate_admin_authorities()
    populate_student_formations_and_branches()
    populate_teacher_grades_and_teachers()
    populate_library_categories_and_articles()
    populate_event_categories()
    populate_activity_sectors()
    populate_internship_categories()
    populate_entreprises()
    populate_study_announcements()
    populate_events()
    populate_internships()
    populate_jobs()
    populate_theses()
    print("Database population complete.")
