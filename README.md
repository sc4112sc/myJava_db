# Eclipse Java 連結 SQL SERVER


第一步
開啟TCP/IP
電腦搜尋 SQL Configuration Manager → TCP/IP的Enable打開

 

第二步
安裝 Microsoft JDBC Driver 6.4 for SQL Server.
下載 JDBC driver https://www.microsoft.com/zh-tw/download/details.aspx?id=56615
並解壓縮。
版本參考 :
https://docs.microsoft.com/zh-tw/sql/connect/jdbc/system-requirements-for-the-jdbc-driver?view=sql-server-ver15


第三步
設定 Eclipse 的ClassPath
在tool bar 選擇 [Run] -> [Run Configurations] -> 右側的 tab [Classpath] -> 在User Entries 下, 點選按鈕 [Add External JARs]
 

因為我選擇用JDBC Driver 6.4版本的，所以JRE最好改成7.0版
可參考以下網址:
下載1.7.0 JDK :https://codenotfound.com/java-download-install-jdk-7-windows.html
JRE設定教學 : https://www.zkoss.org/wiki/Setting_Default_JRE_In_Eclipse

保險起見，這裡也修改一下:
 
 

第四步
撰寫程式碼

 
