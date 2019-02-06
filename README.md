# Oculus Go 用の App Launcher

Oculus Go 上で一般の Android アプリを実行できるようにします。
Android 設定画面も呼び出せます。

## Install

Oculus Go に TVLauncher1.0.apk を install してください。

### 詳細手順

1. PC に Android SDK を install して adb command に PATH を通します
2. Oculus Go を Developer Mode にして PC と USB 接続します
3. adb command を使って TVLauncher1.0.apk を install します

    adb install TVLauncher1.0.apk

## 使い方

1. Oculus Go に Oculus TV を install しておいてください。
2. Oculus TV を起動して、下の方にある "TVLauncherGo" を起動します。

Oculus Go に install されている一般の Android アプリが並んでいるのでクリックで実行できます。

長押しでアプリ詳細画面に飛ぶので、そこで Uninstall もできます。


# apk\_get.py について

Oculus Go に Android アプリを Install するためには apk file が必要です。
このコマンドを使うと、スマートフォンから apk を取り出すことができます。
python 3.x が必要です。

1. 一般の Android スマートフォンでアプリを落とす
2. developer mode にして PC に adb で接続する
3. python apk\_get.py を実行する



