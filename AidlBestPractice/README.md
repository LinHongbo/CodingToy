# AidlBestPractice
AIDL 最佳实践

1. aidl文件封装在aar文件中，Server端跟Client端集成该aar包，避免aidl文件不完全匹配导致通信出问题；
2. aidl生成的java文件，接口权限从public缩小为默认（即包权限）
3. 针对封装aidl接口，对外屏蔽aidl细节和非必要接口。
