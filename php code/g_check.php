<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("ticket"); 
mysql_query("set names UTF8");

$name=$_REQUEST['name'];
$age=$_REQUEST['age'];

$result1=  mysql_result(mysql_query("select status from student where name='$name' AND age='$age';"),0);
$result2=  mysql_result(mysql_query("select time from student where name='$name' AND age='$age';"),0);

 

$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; 
$xmlcode .= "<result1>$result1</result1><result2>$result2</result2>";


$dir = "C:/APM_Setup/htdocs/ticket"; 
$filename = $dir."/g_check.xml";

file_put_contents($filename, $xmlcode); 
?>

