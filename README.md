# ระบบติดตามและรายงานข่าวลือ (Rumour Tracking System) ข้อที่ 1

ระบบติดตามและรายงานข่าวลือ พัฒนาโดยใช้ **Java + Spark Framework + Thymeleaf + SQLite**  
ออกแบบตามแนวคิด **MVC (Model–View–Controller)**  
ผู้ใช้สามารถดูข่าวลือ รายงานข่าวลือ และดูสรุปสถานะของข่าวลือได้

---

## เทคโนโลยีที่ใช้
- Java (แนะนำ JDK 17)
- Spark Framework
- Thymeleaf
- SQLite
- Maven

---

## โครงสร้างโปรเจกต์ (MVC Architecture)

โปรเจกต์นี้พัฒนาโดยใช้แนวคิด **Model–View–Controller (MVC)** เพื่อแยกความรับผิดชอบของระบบออกจากกันอย่างชัดเจน ดังนี้

### Model
- ที่อยู่: `src/main/java/com/example/model`
- หน้าที่:
  - เก็บโครงสร้างข้อมูลของระบบ เช่น Rumour, Report, Verification, User
  - ใช้แทนข้อมูลที่ดึงมาจากฐานข้อมูล

### View
- ที่อยู่: `src/main/resources/templates`
- หน้าที่:
  - แสดงผลข้อมูลให้ผู้ใช้ผ่านไฟล์ HTML (Thymeleaf)
  - ประกอบด้วยหน้า:
    - `rumours.html` หน้ารวมข่าวลือ
    - `rumour-detail.html` หน้ารายละเอียดข่าวลือ
    - `summary.html` หน้าสรุปสถานะข่าว (panic / verified)
    - `report-error.html` หน้าแสดงข้อความ error

### Controller
- ที่อยู่: `src/main/java/com/example/controller`
- หน้าที่:
  - รับ request จากผู้ใช้
  - ประมวลผลร่วมกับ DAO / business logic
  - ส่งข้อมูลไปแสดงผลที่ View
- Controller ที่ใช้ในระบบ:
  - `RumourController`
  - `ReportController`
  - `SummaryController`

### Data Access Layer (DAO)
- ที่อยู่: `src/main/java/com/example/dao`
- หน้าที่:
  - ติดต่อฐานข้อมูล SQLite ผ่าน JDBC
  - แยก logic การ query ออกจาก Controller

### Database
- ฐานข้อมูล: SQLite
- ไฟล์ฐานข้อมูล: `src/main/resources/templates/rumours.db`
- ไฟล์โครงสร้างฐานข้อมูล: `schema.sql`
- ไฟล์ข้อมูลเริ่มต้น: `seed.sql`

---

# Rumour Tracking System

## วิธีรันระบบ

```bash

# สร้างและเปิดฐานข้อมูล
sqlite3 src/main/resources/templates/rumours.db

# วางคำสั่ง schema + seed ลงไป
.read schema.sql
.read seed.sql

# ออกจาก sqlite
.exit

# รันเซิร์ฟเวอร์
mvn clean compile exec:java

```
## เปิดเว็บเบราว์เซอร์

[http://localhost:4567](http://localhost:4567)





