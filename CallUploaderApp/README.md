# CallUploaderApp

A minimal Android Kotlin app that uploads call log details to a **Google Apps Script** endpoint for storage in Google Sheets.

## ✨ Features
- Simple button to trigger a test call log upload.
- Uses OkHttp for networking.
- Ready to compile in **AIDE** on Android (no PC required).
- API key protection for security.

## 📂 Project Structure
```
CallUploaderApp/
 ├── app/
 │   ├── src/main/java/com/example/calluploader/
 │   │   ├── MainActivity.kt
 │   │   └── CallUploader.kt
 │   ├── src/main/res/layout/activity_main.xml
 │   └── src/main/AndroidManifest.xml
 ├── build.gradle
 ├── settings.gradle
 └── README.md
```

## ⚙️ Setup

1. **Google Apps Script**
   - Deploy the script provided earlier (make sure it writes to your Google Sheet).
   - Copy the deployment URL (ending with `/exec`).
   - Note the **API_KEY** you set in the script.

2. **Update the app**
   - Open `CallUploader.kt`.
   - Replace:
     ```kotlin
     private const val API_KEY = "CHANGE_ME_TO_A_SECRET"
     private const val ENDPOINT = "https://script.google.com/macros/s/AKfycbxYourScriptID/exec"
     ```
   - with your actual API key and deployment URL.

3. **Build & Run with AIDE**
   - Upload this project to GitHub (or copy directly to your phone).
   - Open **AIDE** → clone repo or open project folder.
   - Tap **Run** ▶️ → APK will build and install on your phone.

4. **Test Upload**
   - Launch the app.
   - Tap "Upload Test Call".
   - Check your Google Sheet for new row with test data.

## 🔒 Security Notes
- Keep your API key private.
- Restrict Google Apps Script deployment to only the required users if needed.

---
Enjoy building 🚀
