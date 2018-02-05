function activeUser(activeUser) {
	if ("Y" == activeUser || "y" == activeUser) {
		return true;
	} else {
		alert(loginError);
		return false;
	}
	return false;
}

function validateUpload() {
	var image = document.getElementById("file").value;
	if (image != '') {
		var checkimg = image.toLowerCase();
		if (!checkimg.match(/(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG)$/)) {
			alert("Please select valid Image File Extensions .jpg,.png,.jpeg");
			document.getElementById("file").focus();
			return false;
		}
	}
	return true;
}