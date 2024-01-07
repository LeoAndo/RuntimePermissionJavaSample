Javaを使ったRuntime Permissionのサンプル<br>
https://developer.android.com/reference/android/Manifest.permission<br>

# development env
```
Android Studio Jellyfish | 2023.3.1 Nightly 2024-01-04
Build #AI-233.13135.103.2331.11277400, built on January 5, 2024
Runtime version: 17.0.9+0-17.0.9b1087.9-11255266 x86_64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
macOS 14.1.1
GC: G1 Young Generation, G1 Old Generation
Memory: 4096M
Cores: 16
Metal Rendering is ON
Registry:
  debugger.new.tool.window.layout=true
Non-Bundled Plugins:
  wu.seal.tool.jsontokotlin (3.7.4)
```

# capture

<img src="./1.png" width=320 />

# Logcat
```
2024-01-07 14:21:06.514 11483-11483 MainActivity            com...o.runtimepermissionjavasample  D  isDenied: true
2024-01-07 14:21:08.128 11483-11483 MainActivity            com...o.runtimepermissionjavasample  D  granted: true
2024-01-07 14:21:09.541 11483-11483 MainActivity            com...o.runtimepermissionjavasample  D  isDenied: true isDenied2: true
2024-01-07 14:21:14.149 11483-11483 MainActivity            com...o.runtimepermissionjavasample  D  granted: {android.permission.READ_CALENDAR=true, android.permission.READ_PHONE_NUMBERS=true}
2024-01-07 14:21:14.156 11483-11483 MainActivity            com...o.runtimepermissionjavasample  D  isAllGranted: true
```