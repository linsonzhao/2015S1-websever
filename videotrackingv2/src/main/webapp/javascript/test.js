$("#grid").mousedown(function(e) {

	$("#big-ghost").remove();
	$(".ghost-select").addClass("ghost-active");
	$(".ghost-select").css({
		'left' : e.pageX,
		'top' : e.pageY
	});

	initialW = e.pageX;
	initialH = e.pageY;

	$(document).bind("mouseup", selectElements);
	$(document).bind("mousemove", openSelector);

});

function openSelector(e) {
	var w = Math.abs(initialW - e.pageX);
	var h = Math.abs(initialH - e.pageY);

	$(".ghost-select").css({
		'width' : w,
		'height' : h
	});
	if (e.pageX <= initialW && e.pageY >= initialH) {
		$(".ghost-select").css({
			'left' : e.pageX
		});
	} else if (e.pageY <= initialH && e.pageX >= initialW) {
		$(".ghost-select").css({
			'top' : e.pageY
		});
	} else if (e.pageY < initialH && e.pageX < initialW) {
		$(".ghost-select").css({
			'left' : e.pageX,
			"top" : e.pageY
		});
	}
}

function selectElements(e) {
	$("#score>span").text('0');
	$(document).unbind("mousemove", openSelector);
	$(document).unbind("mouseup", selectElements);
	var maxX = 0;
	var minX = 5000;
	var maxY = 0;
	var minY = 5000;
	var totalElements = 0;
	var elementArr = new Array();
	$(".elements").each(
			function() {
				var aElem = $(".ghost-select");
				var bElem = $(this);
				var result = doObjectsCollide(aElem, bElem);

				console.log(result);
				if (result == true) {
					$("#score>span").text(Number($("#score>span").text()) + 1);
					var aElemPos = bElem.offset();
					var bElemPos = bElem.offset();
					var aW = bElem.width();
					var aH = bElem.height();
					var bW = bElem.width();
					var bH = bElem.height();

					var coords = checkMaxMinPos(aElemPos, bElemPos, aW, aH, bW,
							bH, maxX, minX, maxY, minY);
					maxX = coords.maxX;
					minX = coords.minX;
					maxY = coords.maxY;
					minY = coords.minY;
					var parent = bElem.parent();

					// console.log(aElem, bElem,maxX, minX, maxY,minY);
					if (bElem.css("left") === "auto"
							&& bElem.css("top") === "auto") {
						bElem.css({
							'left' : parent.css('left'),
							'top' : parent.css('top')
						});
					}
					$("body").append(
							"<div id='big-ghost' class='big-ghost' x='"
									+ Number(minX - 20) + "' y='"
									+ Number(minY - 10) + "'></div>");

					$("#big-ghost").css({
						'width' : maxX + 40 - minX,
						'height' : maxY + 20 - minY,
						'top' : minY - 10,
						'left' : minX - 20
					});

				}
			});

	$(".ghost-select").removeClass("ghost-active");
	$(".ghost-select").width(0).height(0);

	// //////////////////////////////////////////////

}