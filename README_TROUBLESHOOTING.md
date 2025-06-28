# دليل استكشاف الأخطاء - TaaliibHub

## المشاكل الشائعة وحلولها

### 1. خطأ 403 في تسجيل الدخول
**السبب**: مشكلة في CORS أو Security Configuration
**الحل**:
- تأكد من أن Backend يعمل على المنفذ 8080
- تأكد من أن Frontend يعمل على المنفذ 4200 أو 4201
- أعد تشغيل Backend بعد تحديث SecurityConfig

### 2. خطأ 400 في التسجيل
**السبب**: بيانات غير صحيحة أو مشكلة في DTO
**الحل**:
- تأكد من أن جميع الحقول مملوءة
- تأكد من أن كلمة المرور 8 أحرف على الأقل
- تأكد من أن البريد الإلكتروني صحيح

### 3. خطأ NetworkMonitor Timeout
**السبب**: مشكلة في Service Worker أو اتصال الشبكة
**الحل**:
- أعد تشغيل المتصفح
- امسح cache المتصفح
- تأكد من أن Backend يعمل

### 4. خطأ "Cannot connect to server"
**السبب**: Backend غير مشغل
**الحل**:
```bash
cd backend
mvn spring-boot:run
```

### 5. خطأ في Tailwind CSS
**السبب**: مشكلة في PostCSS أو إصدارات غير متوافقة
**الحل**:
```bash
cd front
npm install tailwindcss@3.4.1 postcss autoprefixer --save-dev
```

## كيفية التشغيل الصحيح

### الخطوة 1: تشغيل Backend
```bash
cd backend
mvn spring-boot:run
```
انتظر حتى تظهر رسالة "Started BackendApplication"

### الخطوة 2: تشغيل Frontend
```bash
cd front
ng serve --port 4201
```

### الخطوة 3: اختبار النظام
1. افتح `http://localhost:4201`
2. جرب التسجيل بحساب جديد
3. جرب تسجيل الدخول بالحساب المُنشأ

## فحص الاتصال

### اختبار Backend API
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"nom":"Test User","email":"test@example.com","password":"password123"}'
```

### اختبار CORS
افتح Developer Tools في المتصفح وانتقل إلى Network tab لمراقبة الطلبات.

## إعدادات قاعدة البيانات

تأكد من أن MySQL يعمل وأن قاعدة البيانات `taalibHub` موجودة:

```sql
CREATE DATABASE IF NOT EXISTS taalibHub;
```

## إعدادات التطبيق

### Backend (application.properties)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/taalibHub
spring.datasource.username=root
spring.datasource.password=1234
```

### Frontend (auth.component.ts)
```typescript
const response = await this.http.post('http://localhost:8080/api/auth/login', loginData).toPromise();
```

## رسائل الخطأ الشائعة

| الخطأ | السبب | الحل |
|-------|-------|------|
| 403 Forbidden | CORS أو Security | أعد تشغيل Backend |
| 400 Bad Request | بيانات غير صحيحة | تحقق من البيانات |
| 500 Internal Server Error | خطأ في Backend | تحقق من logs |
| Network Error | Backend غير مشغل | شغل Backend |

## نصائح للتطوير

1. **استخدم Developer Tools**: لمراقبة Network requests
2. **تحقق من Console**: لرؤية رسائل الخطأ
3. **أعد تشغيل الخوادم**: عند تغيير الإعدادات
4. **تحقق من Ports**: تأكد من عدم تعارض المنافذ

## الدعم

إذا استمرت المشاكل:
1. تحقق من logs Backend
2. تحقق من Console Frontend
3. تأكد من إصدارات Node.js و Java
4. أعد تثبيت التبعيات إذا لزم الأمر 