
package com.boco.eoms.otherwise.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.otherwise.util.OtherwiseConstacts;
import com.boco.eoms.otherwise.webapp.form.TawRmTestcardForm;

public class TawRmTestcardActionTest extends BaseStrutsTestCase {

    public TawRmTestcardActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawRmTestcard");
        addRequestParameter("method", "Save");

        TawRmTestcardForm tawRmTestcardForm = new TawRmTestcardForm();
        // set required fields
        //tawRmTestcardForm.setAscriptionPlace("DyBnNkVnSeIvWtVdDlJhSvPdWxPyBl");
        //tawRmTestcardForm.setVisitPlace("BnMvWeEuMgJfShOgTqYkFiMbHrTjWy");
        tawRmTestcardForm.setSupplyer("MgJuWgUiMqIwNmSlOlUrFyTjQaZdDiPgNyUuTrNhRzXpDcZtTw");
        tawRmTestcardForm.setIccid("HbGvXrGeFaWvHiMlCqUaEbWhUvZoAd");
        tawRmTestcardForm.setMsisdn("HlXqZvEbApIiEaLkFzFvMsQrNlAeDn");
        tawRmTestcardForm.setImsi("IaGtFzLoUkYwAuUzOlDlWjBoRsKjXa");
        tawRmTestcardForm.setPin("CcOmVnRdBvDgLvJeVbQiGqSnRhLhYg");
        tawRmTestcardForm.setPuk("GwAbHoLbQaXxLzRbZhIkDrMmOkVvMz");
        tawRmTestcardForm.setOperation("MwSnEoZyHkAtCnTuDdNxRrQiLsXiCq");
        tawRmTestcardForm.setOpenAccountDate("OiCdDzPbUzBnPiSjZjNqLxQeJpGlSt");
        tawRmTestcardForm.setLogoutDate("VcFhNdSxEmHqAkGrWvNnPvVxHyPiRm");
        tawRmTestcardForm.setTakeOverDate("LgPpBgGaWgXlWeIfWsVxVcUdTxNmHr");
        tawRmTestcardForm.setState("LiHzJjUxFmPeEdRnHuVvToGiOmQkOi");
        tawRmTestcardForm.setOldNumber("GsBcTbIyCuElEbSaQjJaRpHvKfIcZp");

        request.setAttribute(OtherwiseConstacts.TAWRMTESTCARD_KEY, tawRmTestcardForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawRmTestcards");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(OtherwiseConstacts.TAWRMTESTCARD_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawRmTestcard");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(OtherwiseConstacts.TAWRMTESTCARD_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawRmTestcard");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        TawRmTestcardForm tawRmTestcardForm = (TawRmTestcardForm) request.getAttribute(OtherwiseConstacts.TAWRMTESTCARD_KEY);
        //assertNotNull(tawRmTestcardForm);

        setRequestPathInfo("/saveTawRmTestcard");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request
        //tawRmTestcardForm.setAscriptionPlace("UdGnLuJmSvBwXsVzKbAiEhZrVyOgMo");
        //tawRmTestcardForm.setVisitPlace("JvYaQmHaVcBcSdHiBgPcXeMcObPzCj");
        tawRmTestcardForm.setSupplyer("TvRjKzXuHrCdNrUbTrSkQnBrCwTmIgWhSzKoUsXgXxCmNmTqBh");
        tawRmTestcardForm.setIccid("RhKaBkIkDzZoWwSrQtVqVhDnIrUhFg");
        tawRmTestcardForm.setMsisdn("DrWhNaUaCzNdVcDtWmSdMbAhQqBzMf");
        tawRmTestcardForm.setImsi("KfGhAfJtUmGiWxRiCpItThIvKzFgKp");
        tawRmTestcardForm.setPin("NaZeSdHzTbMvZiXjNfRzAiUgZbSlLj");
        tawRmTestcardForm.setPuk("DgImEdFdSuMgXcPfSfEoJeHhTtFbNz");
        tawRmTestcardForm.setOperation("MkCbSrXkAdAnLhKkRdPsMwVoBuHtVt");
        tawRmTestcardForm.setOpenAccountDate("AlFqNpAfPdBzVkUdBlAsFzMfHxEkZc");
        tawRmTestcardForm.setLogoutDate("DbLrRzDdSySpLvVsJkQcNzAkAkUiXo");
        tawRmTestcardForm.setTakeOverDate("YrPtInQdVhSnAkDwPcPfPhQoYgOoDx");
        tawRmTestcardForm.setState("UkGmMkNrOtGsPyUhUbEyGlSfOcFrAs");
        tawRmTestcardForm.setOldNumber("MiVnGhDrXxRdZjRhKtDhIuVzJuKyOw");

        request.setAttribute(OtherwiseConstacts.TAWRMTESTCARD_KEY, tawRmTestcardForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawRmTestcard.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawRmTestcard");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
