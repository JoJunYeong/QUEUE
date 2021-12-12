<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("ticket"); 
mysql_query("set names UTF8");

$no=$_REQUEST['no'];
$t=$_REQUEST['t'];

$result=  mysql_result(mysql_query("select count(name) from student where q1='$no' and q2=0 and status=1;"),0);
$result2=  mysql_result(mysql_query("select count(name) from student where q2='$no' and status=1;"),0);


$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; 
$xmlcode .= "<result>$result</result><result2>$result2</result2>";


$dir = "C:/APM_Setup/htdocs/ticket"; 
$filename = $dir."/t_check_student.xml";

file_put_contents($filename, $xmlcode); 
?>

