BLOCKZYRA Android Project - v1.0.0

WHAT IS INCLUDED
- BLOCKZYRA game HTML in app/src/main/assets/index.html
- BLOCKZYRA app icon
- Android WebView wrapper
- applicationId: com.blockzyra.game
- targetSdk: 36 (Android 16 / Google Play 2026 requirement)
- versionCode: 1
- versionName: 1.0.0

BUILD IN ANDROID STUDIO
1. Open this folder as a project in Android Studio.
2. Let Gradle sync and install the required Android SDK 36 / Build Tools.
3. For a test APK: Build > Build APK(s).
4. For Play Store: Build > Generate Signed Bundle / APK > Android App Bundle.
5. Use Play App Signing when publishing the first release.

IMPORTANT
- This project is the non-payment, non-AdMob build.
- Xsolla/payment and AdMob are intentionally not added yet.
- The HTML uses Firebase and therefore INTERNET permission is already included.
- Before publishing, test login, leaderboard, shop UI, settings, share buttons, and all game flows on a real Android phone.
- Google Play new apps should be uploaded as AAB, not a plain APK.
- Never share your signing keystore/password or Firebase service-account secrets.
