<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("ticket"); 
mysql_query("set names UTF8");

$name=$_REQUEST['name'];
$seat=$_REQUEST['seat'];
$t=$_REQUEST['t'];
$no=$_REQUEST['month'];

$result=  mysql_result(mysql_query("update student set status=1, q2='$no'  where name='$name' and time='$t';"),0);


$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; 
$xmlcode .= "<result>$result</result>";


$dir = "C:/APM_Setup/htdocs/ticket"; 
$filename = $dir."/ok2.xml";

file_put_contents($filename, $xmlcode); 
?>

