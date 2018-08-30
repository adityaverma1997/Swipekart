<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {
	box-sizing: border-box
}

body {
	font-family: Verdana, sans-serif;
	margin: 0;
}

.mySlides {
	display: none
}

img {
	vertical-align: middle;
}

/* Slideshow container */
.slideshow-container {
	max-width: 1000px;
	position: relative;
	margin: -120px;
}

/* Next & previous buttons */
.prev, .next {
	cursor: pointer;
	position: absolute;
	top: 60%;
	width: auto;
	padding: 16px;
	margin-top: -22px;
	color: black;
	background-color:white;
	font-weight: bold;
	font-size: 18px;
	transition: 0.6s ease;
	border-radius: 0 3px 3px 0;
}

/* Position the "next button" to the right */
.next {
	right: -440px;
	border-radius: 3px 0 0 3px;
}

/* On hover, add a black background color with a little bit see-through */
/*.prev:hover, .next:hover {
	background-color: rgba(0, 0, 0, 0.8);
}*/

/* Caption text */
.text {
	color: #f2f2f2;
	font-size: 15px;
	padding: 8px 12px;
	position: absolute;
	bottom: 8px;
	width: 100%;
	text-align: center;
}

/* Number text (1/3 etc) */
.numbertext {
	color: #f2f2f2;
	font-size: 12px;
	padding: 8px 12px;
	position: absolute;
	top: 0;
}

/* The dots/bullets/indicators */
.dot {
	cursor: pointer;
	height: 8px;
	width: 8px;
	margin: 0 2px;
	position:relative;
	bottom:-90px;
	background-color: #000;
	border-radius: 50%;
	display: inline-block;
	transition: background-color 0.6s ease;
}

.actve, .dot:hover {
	background-color: #ff6b81;
}

/*Fading animation*/
.fade {
	-webkit-animation-name: fade;
	-webkit-animation-duration: 1.5s;
	animation-name: fade;
	animation-duration: 1.5s;
}

@
-webkit-keyframes fade {
	from {opacity: .4
}

to {
	opacity: 1
}

}
@
keyframes fade {
	from {opacity: .4
}

to {
	opacity: 1
}

}

/*On smaller screens,decrease text size*/
@media only screen and (max-width: 300px) {
	.prev, .next, .text {
		font-size: 11px
	}
}
</style>
</head>
<body>
	<div class="slideshow-container">

		<div class="mySlides fade">
			<div class="numbertext">1 / 6</div>
			<a href="#"><img src="../slideshow_images/d.jpg" style="width: 1500px;"></a>
			<div class="text"></div>
		</div>

		<div class="mySlides fade">
			<div class="numbertext">2 / 6</div>
			<a href="#"><img src="../slideshow_images/b.jpg" style="width: 1500px;"></a>
			<div class="text"></div>
		</div>
		
		<div class="mySlides fade">
			<div class="numbertext">3 / 6</div>
			<a href="#"><img src="../slideshow_images/c.jpg" style="width: 1500px;"></a>
			<div class="text"></div>
		</div>

		<div class="mySlides fade">
			<div class="numbertext">4 / 6</div>
			<a href="#"><img src="../slideshow_images/a.jpg" style="width: 1500px;"></a>
			<div class="text"></div>
		</div>

		<div class="mySlides fade">
			<div class="numbertext">5 / 6</div>
			<a href="#"><img src="../slideshow_images/e.jpg" style="width: 1500px;"></a>
			<div class="text"></div>
		</div>
		
		<div class="mySlides fade">
			<div class="numbertext">6 / 6</div>
			<a href="#"><img src="../slideshow_images/f.jpg" style="width: 1500px;"></a>
			<div class="text"></div>
		</div>
		
		<a class="prev" onclick="plusSlides(-1)">&#10094;</a> <a class="next"
			onclick="plusSlides(1)">&#10095;</a>

	</div>
	<br>

	<div style="text-align: center;">
		<span class="dot" onclick="currentSlide(1)"></span> <span class="dot"
			onclick="currentSlide(2)"></span> <span class="dot"
			onclick="currentSlide(3)"></span>
		<span class="dot" onclick="currentSlide(4)"></span>
		<span class="dot" onclick="currentSlide(5)"></span>
		<span class="dot" onclick="currentSlide(6)"></span>
	</div>

	<script>
		//var myIndex = 0;
		//carousel();
		var slideIndex = 1;
		showSlides(slideIndex);

		/*function carousel() {
			var i;
			var x = document.getElementsByClassName("mySlides");
			var dots = document.getElementsByClassName("dot");
			for (i = 0; i < x.length; i++) {
				x[i].style.display = "none";
			}
			myIndex++;
			if (myIndex > x.length) {
				myIndex = 1
			}
			for (i = 0; i < dots.length; i++) {
				dots[i].className = dots[i].className.replace(" active", "");
			}
			x[myIndex - 1].style.display = "block";
			dots[myIndex - 1].className += " active";
			setTimeout(carousel, 8000);
		}*/
		function plusSlides(n) {
			showSlides(slideIndex += n);
		}

		function currentSlide(n) {
			showSlides(slideIndex = n);
		}

		function showSlides(n) {
			var i;
			var slides = document.getElementsByClassName("mySlides");
			var dots = document.getElementsByClassName("dot");
			if (n > slides.length) {
				slideIndex = 1
			}
			if (n < 1) {
				slideIndex = slides.length
			}
			for (i = 0; i < slides.length; i++) {
				slides[i].style.display = "none";
			}
			for (i = 0; i < dots.length; i++) {
				dots[i].className = dots[i].className.replace(" actve", "");
			}
			slides[slideIndex - 1].style.display = "block";
			dots[slideIndex - 1].className += " actve";
		}
	</script>
	<script>
var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
var dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}    
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";  
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" actve", "");
  }
  slides[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " actve";
}
</script>

</body>
</html>