<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>JWChat - Mostrar mensaje</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
            <script src="switchStyle.js"></script>
    </head>
    <body>
        <form name="stat_form">
            <select id="status" name="status" onChange="return parent.changeStatus(this.value,true);">
                <option style="background-image:url('images/available.gif');" class="statusbarOpt" value="available">
                    conectado
                </option>
                <option style="background-image:url('images/chat.gif');" class="statusbarOpt" value="chat">disponible
                    para charlar
                </option>
                <option style="background-image:url('images/away.gif');" class="statusbarOpt" value="away">ausente
                </option>
                <option style="background-image:url('images/xa.gif');" class="statusbarOpt" value="xa">no disponible
                </option>
                <option style="background-image:url('images/dnd.gif');" class="statusbarOpt" value="dnd">no molestar
                </option>
                <option style="background-image:url('images/invisible.gif');" class="statusbarOpt" value="unavailable">
                    Invisible
                </option>
                <script language="JavaScript1.2">
                    if (typeof(parent.pass)!='undefined')
                    document.write('<option style="background-image:url(\'images/unavailable.gif\');"
                                            class="statusbarOpt" selected value="offline">desconectado</option>');
                </script>
            </select>
        </form>
    </body>
</html>
