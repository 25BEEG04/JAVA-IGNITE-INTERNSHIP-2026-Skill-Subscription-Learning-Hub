<!--
	Why it is used:
This page shows available training packs / courses / subscription plans.
What it does:
Displays list of courses or packages
Shows price, duration, features
Lets user choose a plan
Why it is needed:
This is the main business page of your system:
It converts users into customers
Helps users decide what to buy/enroll
Simple flow:
User - logs in -views packs - selects a plan
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Skill Packs</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="header">
    <img src="/images/logo.png">
    <h2>Available Skill Packs</h2>

    <!-- show logged in user name -->
    <p>Welcome, ${sessionScope.loggedUser.name}!</p>

    <!-- logout button -->
    <form action="/logout" method="post">
        <input type="submit" value="Logout" style="
            float: right;
            margin-top: 10px;
            padding: 8px 16px;
            background-color: #e74c3c;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 14px;
            cursor: pointer;
        "/>
    </form>
</div>

<div class="container">
    <h3>All Courses</h3>

    <!-- search form -->
    <form action="/packs" method="get">
        <input type="text" name="search" placeholder="Search packs...">
        <button type="submit">Search</button>
    </form>

    <!-- show error if duplicate subscription -->
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>

    <!--  loop skill packs -->
    <c:forEach var="pack" items="${packs}">
        <div class="card">
            <!--  show title -->
            <h4>${pack.title}</h4>
            <!--  show description -->
            <p>${pack.description}</p>
            <!--  show price -->
            <b>₹ ${pack.price}</b>
            <br><br>
            <!-- subscribe action using session userId -->
            <a href="/subscribe?userId=${sessionScope.loggedUser.id}&packId=${pack.id}">
                Subscribe
            </a>
        </div>
    </c:forEach>

    <!-- show total subscriptions -->
    <p>Your Total Subscriptions: ${subscriptionCount}</p>
</div>
</body>
</html>
