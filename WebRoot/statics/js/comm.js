// localhost:8080/AppinfoSystem/user/userList
var localObj=window.location;
//AppinfoSystem
var contextPath=localObj.pathname.split("/")[1];
//localObj.protocol -> http:    localObj.host->localhost:8080    contextPath->AppinfoSystem
var basePath=localObj.protocol+"//"+localObj.host+"/"+contextPath;
var path=basePath;