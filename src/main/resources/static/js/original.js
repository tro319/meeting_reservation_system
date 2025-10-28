


// ハンバーガーメニュー全体閉開処理

const ham = document.querySelector(".hamburgers-wrapper");
const spNav = document.getElementById("sp_nav");
const links = document.querySelectorAll(".hamburger-border");

ham.addEventListener("click", function() {
	
	ham.classList.toggle("click-on");
	spNav.classList.toggle("open");
	
});

links.forEach(function(link) {
	link.addEventListener("click", function() {
		
		ham.classList.remove("click-on");
		spNav.classList.remove("open");
		
	});
});
