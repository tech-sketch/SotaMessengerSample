## Sota Websocket Sample
### サンプルの構成
* server: Tornadoを利用したサーバ側実装 単純に送られてきたメッセージをコネクションのあるクライアントに返しているだけ
* client
    * sotaimple: VstoneMagicで動作済みのクラスサンプル
    * standalonesample: Sota内実装なしでJavaによるWebsocketのクライアント側実装のサンプル
* lib: 依存するライブラリ 今回は[Tyrus](https://tyrus.java.net/ "Project Tyrus")のみ利用

### 使い方
* server: 普通に立ち上げるだけだが、URLの部分は実際に動作する環境に合わせて変える。
* client
    * sotaimple: クラスファイルをVstoneMagic上でインポートし、参照jarの追加からTyrusのjarを追加して利用する。接続先サーバのURL指定は外部ファイル化等していないので、直接変数を実環境に合わせて変更して利用する（リソースファイル化は時間があればやるがなければそのまま）。ただしアノテーションバージョンはVstoneMagicで上書きしないこと。
    * standalonesample: Tyrusのjarにパスが通っている環境で、普通にjavaコマンドから実行するだけ。

### Clinet側sotaimpleでやっていること
単純に音声認識モード移行時にWebsocketでサーバに接続。そのまま認識待ち受け状態を続け、なんらかの音声を認識すると、その内容のテキスト化したものをサーバに送信。現状サーバはecho backしてくるだけの実装なので、認識した言葉をそのままSotaがおしゃべりする。