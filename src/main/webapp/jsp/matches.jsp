<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/style.css">

    <script src="../js/app.js"></script>
</head>

<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="../images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/matches?page=1">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>Matches</h1>
        <form method="get" action="${pageContext.request.contextPath}/matches">
            <div class="input-container">
                <input type="hidden" name="page" value="${param.page}">
                <input class="input-filter" placeholder="Filter by name" type="text" name="filter_by_name" />
                <div>
                    <a href="${pageContext.request.contextPath}/matches?page=1">
                        <button class="btn-filter">Reset Filter</button>
                    </a>
                </div>
            </div>
        </form>

        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <c:forEach var="match" items="${requestScope.matches}">
                <tr>
                    <td>${match.firstPlayer.name}</td>
                    <td>${match.secondPlayer.name}</td>
                    <td><span class="winner-name-td">${match.winner.name} 🏆</span></td>
                </tr>
            </c:forEach>
        </table>

        <c:url var="nextPageUrl" value="${pageContext.request.contextPath}/matches">
            <c:param name="page" value="${param.page + 1}" />
        </c:url>

        <c:url var="lastPageUrl" value="${pageContext.request.contextPath}/matches">
            <c:param name="page" value="${param.page - 1}" />
        </c:url>

        <c:url var="currentPageUrl" value="${pageContext.request.contextPath}/matches">
            <c:param name="page" value="${param.page}" />
        </c:url>

        <div class="pagination">
            <c:if test="${param.page > 1}">
                <a class="prev" href="${lastPageUrl}"> < </a>
            </c:if>

            <c:choose>
                <c:when test="${param.page == 1}">
                    <a class="num-page current" href="${currentPageUrl}">${param.page}</a>

                    <c:if test="${requestScope.totalPages > 1}">
                        <a class="num-page" href="${pageContext.request.contextPath}/matches?page=${param.page + 1}">${param.page + 1}</a>
                    </c:if>

                    <c:if test="${requestScope.totalPages > 2}">
                        <a class="num-page" href="${pageContext.request.contextPath}/matches?page=${param.page + 2}">${param.page + 2}</a>
                    </c:if>
                </c:when>

                <c:when test="${param.page == requestScope.totalPages}">
                    <c:if test="${requestScope.totalPages > 2}">
                        <a class="num-page" href="${pageContext.request.contextPath}/matches?page=${param.page - 2}">${param.page - 2}</a>
                    </c:if>

                    <a class="num-page" href="${pageContext.request.contextPath}/matches?page=${param.page - 1}">${param.page - 1}</a>
                    <a class="num-page current" href="${currentPageUrl}">${param.page}</a>
                </c:when>

                <c:otherwise>
                    <a class="num-page" href="${lastPageUrl}">${param.page - 1}</a>
                    <a class="num-page current" href="${currentPageUrl}">${param.page}</a>
                    <a class="num-page" href="${nextPageUrl}">${param.page + 1}</a>
                </c:otherwise>
            </c:choose>

            <c:if test="${param.page < requestScope.totalPages}">
                <a class="next" href="${nextPageUrl}"> > </a>
            </c:if>

        </div>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </div>
</footer>
</body>
</html>
