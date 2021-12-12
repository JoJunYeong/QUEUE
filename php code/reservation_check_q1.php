<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("ticket"); 
mysql_query("set names UTF8");

$ID=$_REQUEST['ID'];
$t=$_REQUEST['t'];

$result=  mysql_result(mysql_query("select q1 from student where status=0 and time='$t';"),0);
$result2=  mysql_result(mysql_query("select no from teacher where name='$ID';"),0);


$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; 
$xmlcode .= "<result>$result</result><result2>$result2</result2>";


$dir = "C:/APM_Setup/htdocs/ticket"; 
$filename = $dir."/reservation_check_q1.xml";

file_put_contents($filename, $xmlcode); 
?>

