<%@ page import="Classes.Categories" %>
<button type="button" class="btn mt-3 btn-primary bg-success" data-bs-toggle="modal" data-bs-target="#taskModal">
    + Add News
</button>

<!-- Modal -->
<div class="modal fade" id="taskModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="/addNewsServlet" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Add News</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <input type="hidden" class="form-control" name="id" placeholder="Title">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Title</label>
                        <input type="text" class="form-control" name="title" placeholder="Title">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Content</label>
                        <textarea class="form-control" name="content" rows="3"></textarea>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Categories</label>
                        <select name="categories">
                            <%List<Categories> categories = (List<Categories>) request.getAttribute("category");
                                if(categories!=null){
                                    for (Categories categories1 : categories){
                                        %>
                                <option value="<%=categories1.getId()%>"><%=categories1.getName()%></option>
                            <%
                                    }
                                }
                            %>
                        </select>
<%--                        <input type="text" class="form-control" name="categories" placeholder="Number of Category">--%>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
                <div class="explain mt-3 mb-3 row row-cols-1 row-cols-md-2 g-4" style="display: block; padding: 0 40px">
                    <div class="col-sm-6 mb-3 mb-sm-0" style="display: inline-flex; justify-content: space-between;width: 100%; grid-template-columns: repeat(2, 1fr);">
                        <p style="margin: 0"> 1 - Technology</p>
                        <p style="margin: 0"> 2 - Entertainment</p>
                        <p style="margin: 0"> 3 - Sports</p>
                        <p style="margin: 0"> 4 - Scient</p>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
