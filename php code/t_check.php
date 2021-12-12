<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("ticket"); 
mysql_query("set names UTF8");


$result1=  mysql_result(mysql_query("select available from teacher where no=1;"),0);
$result2=  mysql_result(mysql_query("select available from teacher where no=2;"),0);
$result3=  mysql_result(mysql_query("select available from teacher where no=3;"),0);
$result4=  mysql_result(mysql_query("select available from teacher where no=4;"),0);
 

$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; 
$xmlcode .= "<result1>$result1</result1>\n<result2>$result2</result2>\n<result3>$result3</result3>\n<result4>$result4</result4>\n";


$dir = "C:/APM_Setup/htdocs/ticket"; 
$filename = $dir."/t_check.xml";

file_put_contents($filename, $xmlcode); 
?>

