
const ham = document.querySelector(".hamburgers-wrapper");
const links = document.querySelectorAll(".hamburger-border");

ham.addEventListener("click", function() {
	
	ham.classList.toggle("click-on");
	
});

links.forEach(function(link) {
	link.addEventListener("click", function() {
		
		ham.classList.remove("click-on");
		
	});
});