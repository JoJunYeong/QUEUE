<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("ticket"); 
mysql_query("set names UTF8");

$ID=$_REQUEST['ID'];
$no=$_REQUEST['no'];

$result=  mysql_result(mysql_query("select name from student where q1='$no' and q2=0;"),0);
$result2=  mysql_result(mysql_query("select name from student where q2='$no';"),0);
$result3=  mysql_result(mysql_query("select seat from student where q1='$no' and q2=0;"),0);
$result4=  mysql_result(mysql_query("select seat from student where q2='$no';"),0);


$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; 
$xmlcode .= "<result>$result</result><result2>$result2</result2><result3>$result3</result3><result4>$result4</result4>";


$dir = "C:/APM_Setup/htdocs/ticket"; 
$filename = $dir."/ticket_check.xml";

file_put_contents($filename, $xmlcode); 
?>

