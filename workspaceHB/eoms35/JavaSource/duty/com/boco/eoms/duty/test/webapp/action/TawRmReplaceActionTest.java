
package com.boco.eoms.duty.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.duty.webapp.form.TawRmReplaceForm;

public class TawRmReplaceActionTest extends BaseStrutsTestCase {

    public TawRmReplaceActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawRmReplace");
        addRequestParameter("method", "Save");

        TawRmReplaceForm tawRmReplaceForm = new TawRmReplaceForm();
        // set required fields
        tawRmReplaceForm.setDutydate("KyClOmLoJcUrRbMgJvMkEtEsXpYwZg");
        tawRmReplaceForm.setFlag("PzQiJlGkAiZaOoYyNjJrUjVaQlSuNp");
        tawRmReplaceForm.setHander("IqFpIwVeOfVnTeJpTeVsIeUhXvKgWf");
        tawRmReplaceForm.setInputdate("PfJhZiIhGmMeBuOgCzYvEqIyBgAdIk");
        tawRmReplaceForm.setReason("LmMoSyRqSwLnLsUnQzDrUzVlNfXnIgAcDhIgWjKmJuIwMdHmCeFqUyWlRbBfBfTrTrWpLwLsHhOsWpDwVxQpIkZjXtVhZlGkKxJyOmTeYpYoRnBjVsTwSeEsOuJsNoGdQpPbRxHtRgOyCwLuKpTlQuBpMoFvQrInIaDzHeVtTwWlGcUrYpSkQwEtPtCcHpJgVlFyKgBsHwDdDcQjVhQlZfPwKtZbEoWtTdKtUaUdVnCaClPbPyFyPoZbFmJmMePzRkVaSiMtQpYzZyRmFhIxOePtFgYsCjSnBrJeYuAtXqKq");
        tawRmReplaceForm.setReceiver("EjXiZmCjPfRmWeDoWaWwPgOkVbSsLz");
        tawRmReplaceForm.setRemark("IvOoDpHfFcXtEmWiMaKlPbXdCtWdQiMvEdPnDkUdKkCpQxXwSrVgFpCvGtGjBnVrAoCbYuUnAhUbHvBsHvYqRkWwDfAwHxAtUwTmKfAhFtXwMeCyKpQoRgWgFaBmQpIbPnGsAqKaCmQsIaHgWkKqGhEhJrOjRuBiNpMlCqJkFtMsOdMoKmMuOzBmTlWmXeHoKiCrYaCzJlMfTrAfHfPoNcCeMkHyHfMlCkGlPkIcLnYaPjRzCuTiLbKqXiFqLyVfZzNaOfDwWoDkTqWsUdNcBqChSwUiYpMzKoGxYxGvWrLc");
        tawRmReplaceForm.setRoomId("LgAzAeEjQsIlAcLtRcCuVoDmSoIlKr");
        tawRmReplaceForm.setWorkserial("OsZzPcPdQePgYsOjVbReVnZnSwJhRe");

        request.setAttribute(Constants.TAWRMREPLACE_KEY, tawRmReplaceForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawRmReplaces");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.TAWRMREPLACE_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawRmReplace");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.TAWRMREPLACE_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawRmReplace");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        //TawRmReplaceForm tawRmReplaceForm = (TawRmReplaceForm) request.getAttribute(Constants.TAWRMREPLACE_KEY);
        //assertNotNull(tawRmReplaceForm);

        setRequestPathInfo("/saveTawRmReplace");
        addRequestParameter("method", "Save");
  
        // update the form's required string fields and add it back to the request
       /* tawRmReplaceForm.setDutydate("ZjEeStSiIiXoXzDpMdDqHeInCgQrUp");
        tawRmReplaceForm.setFlag("DmDeSsDwQjGdVeOoTeFgIxEyCsWrVn");
        tawRmReplaceForm.setHander("EfIiRcMtJlZdPaSeEsFgKqAoCzDeDg");
        tawRmReplaceForm.setInputdate("XmMvWxMtXnQjUpQdTkYiKoNrLcMtSn");
        tawRmReplaceForm.setReason("FjBsCsIkPsEdLgEgIxErZnCjFpKnPmOuFnBpKrViKhZcIpUjKzZoSoLgKlYcTzDsWwIbFhKvCgPqSnAvRlUbMnSdGtAuTtHwFzSvUkJlKyHtAjDbQfZaQiJoPcGoVnAkBbZoOsYxSuTcFvSjDtDgSiKhOiJfMvSvEdDoAyFlBuAmGfMjQbMiWoUcJpNeWvIfDpGcQwGbVmMiCyDpFtZmOyHsNcYtIpFkDcJaKwOjMaGnXiRqLlLqPcNgCmYrBnRvDpAnFsSiLlRmFqXkYsRwGvFrKfErGwHdMpJyVjEpJoFn");
        tawRmReplaceForm.setReceiver("VkYkPrJaUoUoWnWnHrEmKtJjXhStSo");
        tawRmReplaceForm.setRemark("KjQuOrSkXpBlCgCdSfGaPrZqGtJzPdIfHcQoBpSkKwQeKbHvThTjVcJzLcXmFsQkZbNbXiVbFjXmWwUoPsXdGfErJbYpFeRmJhEiLcEtLlNtVvAqSvQtRyDoUoOzNmThGbPwVaDzZbEsVmPgJjCtYwBgKaZmQjAiTuFiJbUwKcLrLeOfIaRbUyCaKjCdPlSaSyAlUiVtPrDuRgDaGeTwAgXfRvEwPhBjPnIlElMeFvRqOhQxYwBiQdRvYiDzHtCwPoSnTiXzRgVdUsKpGvHuSpShGaWgGvCnWyEqGfUyDpOp");
        tawRmReplaceForm.setRoomId("UmGwWdUiAxYnVxQaKlVlDnKlKwAgCv");
        tawRmReplaceForm.setWorkserial("MaEiDpLuNcHkAhAzByGqRcNmNsLzUo");
*/
        //request.setAttribute(Constants.TAWRMREPLACE_KEY, tawRmReplaceForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawRmReplace.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawRmReplace");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
