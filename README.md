# CS6360-F22-Telemedicine
 Group project for telemedicine application for CS6360 for Fall 2022


# Team
- Vedant Sapra (vks220000) (Team Leader)
- Haoyan Li (hxl210024)
- Roshan Pardiwala (rnp190001)
- Mrinalini Raghavendran (mxr210111)
- Akihiro Yoshimoto (afy180000)


# Database setup guide
Setup MySQL -> https://dev.mysql.com/downloads/installer/

Please run all files in the /SQL package in the following order:
1. CREATE_TABLES.sql
2. INSERT.sql
3. VIEWS.sql
4. COMPLEX_QUERIES.sql

# Springboot application setup guide
Setup VSCode -> https://code.visualstudio.com/

1. Make sure to get relevant extensions for maven -> *Extension Pack for Java*
2. From the Maven side panel in VS Code, run the following tasks in the lifecycle dropdown - clean, install
3. Run file ```TelemedicineApplication.java```
4. Update your username and password as per your myswl configuration at ```src/main/resources/spring.xml```
5. If you are encountering errors while connecting to the DB, open your MySQL isntaller and reconfigure MySQL server and use legacy authentication
6. Test by starting application and using browser to access ```http://localhost:8080/superuser/getAllUsers```