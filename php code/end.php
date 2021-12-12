<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("ticket"); 
mysql_query("set names UTF8");


$name=$_REQUEST['name'];
$age=$_REQUEST['age'];
$seat=$_REQUEST['seat'];

$result2=  mysql_result(mysql_query("select q1 from student where name='$name' and age='$age';"),0);
$result3=  mysql_result(mysql_query("select q2 from student where name='$name' and age='$age';"),0);


$qry = "delete from student where name='$name' and age='$age';";
$result = mysql_query($qry);




$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; 
$xmlcode .= "<result>$result</result><result2>$result2</result2><result3>$result3</result3>";


$dir = "C:/APM_Setup/htdocs/ticket"; 
$filename = $dir."/end.xml";

file_put_contents($filename, $xmlcode); 
?>

