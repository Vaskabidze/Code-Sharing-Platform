function send() {
   let object = {
        code: document.getElementById("code_snippet").value,
         time: document.getElementById("time_restriction").value,
          views: document.getElementById("views_restriction").value
    };
    let json = JSON.stringify(object);
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange= function() {
        if (this.readyState!==4) return; // not ready yet
        if (this.status===200) { // HTTP 200 OK
            alert("Success! " + this.responseText);
        } else {
             alert("Error! Text longer than 255 characters");
        }
    };
    xhr.open("POST", '/api/code/new', false)
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);
}

function goToPage(){
    var url = "/code/" + document.getElementById('redirect').value;
	document.location.href = url;
}
