<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../partials/header.jsp" />
<style>
    table td {
        border-collapse: collapse;
        border: 1px solid #000;
        width: 40px;
        height: 40px;
        cursor: pointer;
        text-align: center;
        vertical-align: middle;
    }
</style>
<p><a href="/">Back to main page</a></p>
<table data-game="${game.id}">
    <c:forEach var="i" begin="0" end="2">
        <tr>
            <c:forEach var="j" begin="0" end="2">
                <c:set var="key">${i}_${j}</c:set>
                <td<c:if test="${board[key] == null}"> data-checked="false"</c:if> data-row="${i}" data-col="${j}">
                    <c:if test="${board[key] != null}">
                       ${board[key].symbol}
                    </c:if>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
<script>
    $(function(){
        $(document).on("click", "td[data-checked=false]", function(){
            var $cell = $(this);
            $cell.text("X");

            var data = {};
            data.game = $("table").attr("data-game");
            data.row = $cell.attr("data-row");
            data.col = $cell.attr("data-col");

            $.post("/make-step", data, function(json) {
                if(json.success) {
                    var computer = json.computer;
                    console.log(json);
                    if(computer) {
                        $("td[data-row=" + computer.row + "][data-col=" + computer.col + "]").text("O").removeAttr("data-checked");
                    }
                    if(json.finished) {
                        alert(json.status);
                    }
                    $cell.removeAttr("data-checked");
                } else {
                    alert(json.error);
                }
            });

            return false;
        });
    });
</script>
<c:import url="../partials/footer.jsp" />