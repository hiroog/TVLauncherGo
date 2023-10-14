# Meta Quest 用の簡易 2D App Launcher

Meta Quest 上で一般の Android アプリを実行できるようにします。
Android 設定画面も呼び出せます。
また表示サイズを 3種類から選択できます。

v1.1.1 で Meta Quest 3 に対応しました。


## Install

何らかの方法を使って Quest 本体に apk をインストールしてください。

* Meta Quest に TVLauncher1.1.x.apk を install してください。
* Oculus Go は以前のバージョンをお使いください。



## 使い方

1. アプリライブラリを開き、検索欄をクリックしします。
2. 右上のプルダウンから「すべて」を「提供元不明」に切り替えます。
3. TVLauncherGo ～ を選択すると起動できます。

ウィンドウサイズを 3種類から選択することができます。

* TVLauncherGo デフォルトサイズ
* TVLauncherGo Large サイズ
* TVLauncherGo Small サイズ

インストールされているアプリ一覧が並ぶので選択すると起動できます。

長押しでアプリ詳細画面に飛ぶので、そこで Uninstall もできます。



# apk\_get.py について

Oculus Go に Android アプリを Install するためには apk file が必要です。
このコマンドを使うと、スマートフォンから apk を取り出すことができます。
python 3.x が必要です。

1. 一般の Android スマートフォンでアプリを落とす
2. developer mode にして PC に adb で接続する
3. python apk\_get.py を実行する



