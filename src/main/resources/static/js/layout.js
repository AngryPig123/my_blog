// scripts.js
const mainMenu = document.querySelector('.main-menu');
const bodyContent = document.querySelector("#body_content");

const bodyContentTemplate = {
    init: function (boardContent) {

    },
    callContent: function (enbPoint) {
        const payLoad = new FormData(document.forms[0]);
        fetch(enbPoint, {
            method: "POST",
            body: payLoad,
        }).then((response) => {
            if (response.ok) {
                return response.text();
            } else {
                //  ToDO script 에러도 로그에 남길것인지.
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
        }).then((data) => {
            bodyContent.innerHTML = data;
        }).catch((error) => {
            console.error(error);
        }).finally(() => {
            console.log("finally");
        });
    }
}
