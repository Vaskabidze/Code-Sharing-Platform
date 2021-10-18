# Code-Sharing-Platform
A Spring boot multi-user web service that allows save and share your code.

Technology stack:
1) Spring Boot, Data, MVC
2) Hibernate, H2 Database
3) Apache Tomcat
3) Rest API
4) Thymeleaf
5) JSON
6) Project Lombok

Data exchange takes place via REST API or user interface.

The following endpoints exist.

User Interface:

GET /code/new 
New code uploading page. User can upload their code. The loaded code will be automatically assigned a UUID. In the future, the code will be available to everyone, 
in the menu of the last loaded codes or by direct link via / code / {uuid}.
The user can set a time limit or the number of views. In this case, the code will be available only through a direct link through / code / {uuid} or through the menu Secret Code.
After reaching the limit, the code will not be available to anyone.

GET /code/secretcode
Page with code by UUID. It is possible to access public code, and code with restrictions. If the code has restrictions, they will be shown on the page.
The code can also be available by a direct link /code/{uuid}

GET /code/latest
The page with the last uploaded public codes. Restricted codes will not be displayed.

API:

POST /api/code/new
Receives JSON with new code for loading into the database. As an option, there can be two fields in JSON with a time limit or number of views.
If one of the restrictions is set, the code will not be public.

GET /api/code/{uuid}
Returns JSON with the specified UUID. Public or restricted. The JSON will contain information about the constraints if they are set for the code.
If the UUID code is not found in the database, the service respond with the 404 (Not Found) status code.

GET /api/code/latest 
Returns JSON with the most recently uploaded public codes. Restricted codes will not be displayed.
