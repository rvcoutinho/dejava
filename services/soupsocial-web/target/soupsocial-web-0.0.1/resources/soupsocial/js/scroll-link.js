$(document).ready(function() {	
	$('a.scroll').click(function(event) {
		// If the path is not "/".
		if ((window.location.pathname != "/") && (window.location.pathname != '') && (event.target.href.indexOf(window.location.pathname) != -1)) {
			// Prevents the link to have its regular behavior.
			event.preventDefault();
			// Scroll the page to the link reference.
			$('html, body').animate({
					scrollTop: $(this.hash).offset().top
			}, 1000);
		}
	});
});