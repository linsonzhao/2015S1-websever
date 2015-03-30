<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myTable.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myNav.css">
<title>animal</title>
</head>
<body background="<%=request.getContextPath()%>/images/images.jpg">
	<%@ include file="navigation.jsp"%>
	<h1>&nbsp</h1>
	<h1>&nbsp</h1>
	<center>
		<h1>Animals</h1>

	</center>
	<h1>&nbsp</h1>
	<div id="page-wrap">
		<table class="myTable">
			<tbody>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/animal_images/kangaroo.jpg"
						alt="Kangaroo" style="width: 304px; height: 228px"></td>
					<td>The word "kangaroo" derives from the Guugu Yimithirr word
						gangurru, referring to grey kangaroos. The name was first recorded
						as "kanguru" on 12 July 1770 in an entry in the diary of Sir
						Joseph Banks; this occurred at the site of modern Cooktown, on the
						banks of the Endeavour River, where HMS Endeavour under the
						command of Lieutenant James Cook was beached for almost seven
						weeks to repair damage sustained on the Great Barrier Reef. Cook
						first referred to kangaroos in his diary entry of 4 August. Guugu
						Yimithirr is the language of the people of the area. A common myth
						about the kangaroo's English name is that "kangaroo" was a Guugu
						Yimithirr phrase for "I don't understand you." According to this
						legend, Cook and Banks were exploring the area when they happened
						upon the animal. They asked a nearby local what the creatures were
						called. The local responded "Kangaroo", meaning "I don't
						understand you", which Cook took to be the name of the creature.
						This myth was debunked in the 1970s by linguist John B. Haviland
						in his research with the Guugu Yimithirr people.</td>
					<td>Rating:<img
						src="<%=request.getContextPath()%>/images/star_full.png"
						alt="star_full" style="width: 24px; height: 24px"> <img
						src="<%=request.getContextPath()%>/images/star_full.png"
						alt="star_full" style="width: 24px; height: 24px"> <img
						src="<%=request.getContextPath()%>/images/star_full.png"
						alt="star_full" style="width: 24px; height: 24px"> <img
						src="<%=request.getContextPath()%>/images/star_full.png"
						alt="star_full" style="width: 24px; height: 24px"> <img
						src="<%=request.getContextPath()%>/images/star_full.png"
						alt="star_full" style="width: 24px; height: 24px"></td>
				</tr>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/animal_images/koala.jpg"
						alt="Kangaroo" style="width: 304px; height: 228px"></td>
					<td>The word koala comes from the Dharug gula. Although the
						vowel 'u' was originally written in the English orthography as
						"oo" (in spellings such as coola or koolah), it was changed to
						"oa", possibly in error. The word is mistakenly said to mean
						"doesn't drink". Because of the koala's resemblance to a bear, it
						was often miscalled the koala bear, particularly by early
						settlers. Other names like monkey bear, native bear, and tree-bear
						have also been used. Indigenous names include cullawine,
						koolawong, colah, karbor, colo, coolbun, boorabee, burroor,
						bangaroo, pucawan, banjorah, and burrenbong; many of these mean
						"no drink". The generic name, Phascolarctos, is derived from the
						Greek words phaskolos "pouch" and arktos "bear". The specific
						name, cinereus, is Latin for "ash coloured".</td>
					<td>Rating:<img
						src="<%=request.getContextPath()%>/images/star_full.png"
						alt="star_full" style="width: 24px; height: 24px"> <img
						src="<%=request.getContextPath()%>/images/star_full.png"
						alt="star_full" style="width: 24px; height: 24px"> <img
						src="<%=request.getContextPath()%>/images/star_full.png"
						alt="star_full" style="width: 24px; height: 24px"> <img
						src="<%=request.getContextPath()%>/images/star_full.png"
						alt="star_full" style="width: 24px; height: 24px"> <img
						src="<%=request.getContextPath()%>/images/star_full.png"
						alt="star_full" style="width: 24px; height: 24px"></td>
				</tr>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/animal_images/emu.jpg"
						alt="Kangaroo" style="width: 304px; height: 228px"></td>
					<td>Emus are large birds. The largest can reach up to 1.5-1.9
						m (4.9-6.2 ft) in height, 1-1.3 m (3.3-4.3 ft) at the shoulder. In
						length measured from the bill to the tail, emus range from 139 to
						164 cm (55 to 65 in), with males averaging 148.5 cm (58.5 in) and
						females averaging 156.8 cm (61.7 in). Emus weigh between 18 and 60
						kg (40 and 132 lb), with an average of 31.5 and 36.9 kg (69 and 81
						lb) in males and females, respectively. Females are usually larger
						than males by a small amount, and are substantially wider across
						the rump.</td>
					<td>Rating:<img
						src="<%=request.getContextPath()%>/images/star_full.png"
						alt="star_full" style="width: 24px; height: 24px"> <img
						src="<%=request.getContextPath()%>/images/star_full.png"
						alt="star_full" style="width: 24px; height: 24px"> <img
						src="<%=request.getContextPath()%>/images/star_full.png"
						alt="star_full" style="width: 24px; height: 24px"> <img
						src="<%=request.getContextPath()%>/images/star_full.png"
						alt="star_full" style="width: 24px; height: 24px"> <img
						src="<%=request.getContextPath()%>/images/star_full.png"
						alt="star_full" style="width: 24px; height: 24px"></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>