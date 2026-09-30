BLOCKZYRA GitHub Build

1. Upload ALL files/folders inside this BLOCKZYRA-Android folder to the ROOT of your GitHub repository.
2. Make sure .github/workflows/android-build.yml is included.
3. Push/upload to the main branch.
4. GitHub Actions will automatically build a debug APK and a release AAB.
5. Open GitHub -> Actions -> BLOCKZYRA Android Build -> latest successful run -> Artifacts -> BLOCKZYRA-Android-Builds.
6. Download the APK to test/install on your phone.
7. The AAB is for the Google Play Console. It is not the APK you install directly.

Important: the release AAB is not signed yet. For the final Play Store release, configure a proper upload/release signing key in GitHub Actions before publishing. Never commit a private keystore/password to the repository.

Current version: 1.0.0
Application ID: com.blockzyra.game
Target SDK: 36
