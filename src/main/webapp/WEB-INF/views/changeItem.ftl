<html>
<head><title>change item</title></head>
<body>
<div id="header">
	<H2 style="color:b">
		change and save
	</H2>
</div>

<div id="content">
  <fieldset>
  	<form name="changeItem" action="${changeItem.id}/save.html" method="post">
  		proName: <input type="text" name="proName" value="${changeItem.proName}"/> </tr> </br>
  		proDesc: <input type="text" name="proDesc" value="${changeItem.proDesc}"/> </tr> </br>
  		phoneNum: <input type="text" name="phoneNum" value="${changeItem.phoneNum}"/> </tr> </br>
  		email: <input type="text" name="email" value="${changeItem.email}"/> </tr> </br>
  		<input type="submit" value="   save  " />
  	<form>
  </fieldset>
</div>  
</body>
</html>  