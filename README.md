# 🗂️ Oracle Task Manager
A cloud-native task management platform built with Java Spring Boot, Oracle Autonomous Database, and Kubernetes. Designed for development teams, it streamlines task tracking, reporting, and collaboration—enhanced by a Telegram chatbot for real-time communication.

# 🚀 Features
- ✅ Task Assignment & Status Tracking

- 🔄 Task Dependencies & Blocking Notifications

- 📈 KPI Aggregator Microservice for team performance insights

- 🤖 Telegram Bot Integration — view tasks, get updates, and interact with your team directly via Telegram

- 🧠 Microservices Architecture hosted on Oracle Kubernetes Engine (OKE)

- 🔐 JWT-based Authentication for secure user access

# 🛠️ Tech Stack
### Backend: Java, Spring Boot, Spring Security, JPA

### Database: Oracle Autonomous Database (ATP)

### Messaging: Telegram Bot API

### Cloud Infrastructure: Oracle Cloud Infrastructure (OCI), Kubernetes (OKE)

### Build & Dependency Management: Maven

# ⚙️ Microservices Overview
## Microservice	Description
AuthService |	Manages users, teams, and authentication
TaskService	| Core logic for task CRUD operations
KPIService	| Aggregates task data and computes KPIs
TelegramBotService	| Sends real-time task updates to Telegram chats


# 📦 How to Run
### Clone the repository:


git clone https://github.com/y/oracle-task-manager.git
cd oracle-task-manager
Set environment variables (.env or directly in your deployment settings):

DB_URL

DB_USER

DB_PASSWORD

JWT_SECRET

TELEGRAM_BOT_TOKEN

Build with Maven:

bash
Copy
Edit
mvn clean install
Deploy to OCI Kubernetes:

Apply deployment and service YAMLs via kubectl

Use Ingress for external access

Configure OKE node pools and secrets as needed

📊 KPI Dashboard (Optional)
If integrated, the KPI microservice can generate:

Task completion rates

Team efficiency stats

Task duration and blockers frequency

