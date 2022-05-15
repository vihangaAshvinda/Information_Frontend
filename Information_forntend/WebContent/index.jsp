<%@page import="model.Information"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (request.getParameter("InformationID") != null) 
{ 
 Information InformationObj = new Information(); 
 String stsMsg = ""; 
//Insert--------------------------
if (request.getParameter("hidInformationIDSave") == "") 
 { 
 stsMsg = InformationObj.insertInformation(request.getParameter("InformationID"), 
 request.getParameter("category"), 
 request.getParameter("InformationName"), 
 request.getParameter("summary"),
 request.getParameter("status")); 
 } 
else//Update----------------------
 { 
 stsMsg = InformationObj.updateInformation(request.getParameter("hidInformationIDSave"), 
 request.getParameter("InformationID"), 
 request.getParameter("category"), 
 request.getParameter("InformationName"),
 request.getParameter("summary"), 
 request.getParameter("status")); 
 } 
 session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hidInformationIDDelete") != null) 
{ 
 Information InformationObj = new Information(); 
 String stsMsg = 
 InformationObj.deleteInformation(request.getParameter("hidInformationIDDelete")); 
 session.setAttribute("statusMsg", stsMsg); 
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Information</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Information V10.1</h1>
<form id="formInformation" name="formInformation">
 Information ID: 
 <input id="InformationID" name="InformationID" type="text" 
 class="form-control form-control-sm">
 <br> category: 
 <input id="category" name="category" type="text" 
 class="form-control form-control-sm">
 <br> Information Name: 
 <input id="InformationName" name="InformationName" type="text" 
 class="form-control form-control-sm">
 <br> summary: 
 <input id="summary" name="summary" type="text" 
 class="form-control form-control-sm">
 <br> status: 
 <input id="status" name="status" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidInformationIDSave" 
 name="hidInformationIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Information InformationObj = new Information(); 
 out.print(InformationObj.readInformation()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
