<%-- 
    Document   : index
    Created on : Mar 2, 2023, 1:41:38 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="utils.Utils" %>
<h1>Product goes here</h1>
Sort by:
<%--    <form action="<c:url value=""/>">
    <label for="sortChoice"></label>
    <select name="sortChoice" id="sortChoice">
        <option value="price">Price</option>
        <option value="brand">Brand</option>
    </select>
</form>--%>

<form action="<c:url value="/home/sort.do"/>">
    <label for="sortOrderChoice"></label>
    <select name="sortOrderChoice" id="sortOrderChoice">
        <option value="asc">Ascending</option>
        <option value="desc">Descending</option>
    </select>
    <button type="submit" name="op" value="sortOrderChoice">Sort</button>
</form>

<div style="display: flex; flex-wrap: wrap; align-content: center; justify-content: space-around">
  
    <c:forEach items="${list}" var="product" varStatus="loop" end="8">
        <!-- Single -->
        <div class="product-single product-page">
            <div class="sale-badge">
                <span>sale</span>
            </div>
            <div class="product-thumbnail">
                <a href="<c:url value="/product/index.do?id=${product.productId}"/>"
                   ><img
                        src="<c:url value="${product.imgSrc}"/>"
                        alt="product"
                        /></a>
                <div
                    class="product-thumbnail-overly"
                    >
                    <ul>
                        <li>
                            <a href="cart.html"
                               ><i
                                    class="fas fa-shopping-cart"
                                    ></i
                                ></a>
                        </li>
                        <li>
                            <a href="wishlist.html"
                               ><i
                                    class="far fa-heart"
                                    ></i
                                ></a>
                        </li>
                        <li>
                            <a href="#"
                               ><i
                                    class="far fa-eye"
                                    ></i
                                ></a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="product-content">
                <h4>
                    <a href="product-details.html"
                       >${product.productName}</a
                    >
                </h4>
                <div class="pricing">
                    <span
                        >$ ${product.price} <del>${product.price+Utils.getRandomNumber()}</del></span
                    >
                </div>
            </div>
        </div>
    </c:forEach>
</div><%--handle logic for the pagination --%>
     <c:if test="${numOfPages >= 1}">
            <c:set var="startPage" value="${currentPage - 5}"/>
            <c:if test="${startPage lt 1}">
                <c:set var="startPage" value="1"/>
            </c:if>
            <c:set var="endPage" value="${currentPage + 4}"/>
            <c:if test="${endPage gt numOfPages}">
                <c:set var="endPage" value="${numOfPages}"/>
            </c:if>
            <div class="pagination justify-content-center">
                <c:choose>
                    <c:when test="${currentPage gt 1}">
                        <button class="page-item"><a href="<c:url value='/home/product.do?currentPage=${currentPage - 1}'/>" ><i class="fa-sharp fa-solid fa-arrow-left"></i></a></button>
                    </c:when>
                    <c:otherwise>
                        <button class="disabled page-item"><i class="fa-sharp fa-solid fa-arrow-left"></i></button>
                    </c:otherwise>
                </c:choose>
                <c:forEach begin="${startPage}" end="${endPage}" var="i">
                    <c:choose>
                        <c:when test="${i eq currentPage}">
                            <button class="actived"><a href="#"><c:out value="${i}"/></a></button>
                        </c:when>
                        <c:otherwise>
                            <button class="page-item"><a href="<c:url value='/home/product.do?currentPage=${i}'/>"><c:out value="${i}"/></a></button>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${currentPage lt numOfPages}">
                        <button clas="page-item"><a href="<c:url value='/home/product.do?currentPage=${currentPage + 1}'/>"><i class="fa-sharp fa-solid fa-arrow-right"></i></a></button>
                    </c:when>
                    <c:otherwise>
                    <button class="disabled page-item"><i class="fa-sharp fa-solid fa-arrow-right"></i></button>
                        </c:otherwise>
                            </c:choose>
          
            </div>
      </c:if>