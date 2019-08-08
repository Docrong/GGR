// Stub class generated by rmic, do not edit.
// Contents subject to change without notice.

package com.boco.eoms.sheet.base.service.ejb;

import commonj.sdo.DataObject;

import java.io.Serializable;
import java.lang.Exception;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.rmi.RemoteException;
import java.rmi.UnexpectedException;
import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.ejb.Handle;
import javax.ejb.RemoveException;
import javax.rmi.CORBA.Stub;
import javax.rmi.CORBA.Util;

import org.omg.CORBA.SystemException;
import org.omg.CORBA.portable.ApplicationException;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;
import org.omg.CORBA.portable.RemarshalException;
import org.omg.CORBA.portable.ServantObject;

public class _MessageService_Stub extends Stub implements MessageService {

    private static final String[] _type_ids = {
            "RMI:com.boco.eoms.sheet.base.service.ejb.MessageService:0000000000000000",
            "RMI:javax.ejb.EJBObject:0000000000000000"
    };

    public String[] _ids() {
        return _type_ids;
    }

    public EJBHome getEJBHome() throws RemoteException {
        while (true) {
            if (!Util.isLocal(this)) {
                InputStream in = null;
                try {
                    try {
                        OutputStream out = _request("_get_EJBHome", true);
                        in = _invoke(out);
                        return (EJBHome) in.read_Object(EJBHome.class);
                    } catch (ApplicationException ex) {
                        in = ex.getInputStream();
                        String id = in.read_string();
                        throw new UnexpectedException(id);
                    } catch (RemarshalException ex) {
                        continue;
                    }
                } catch (SystemException ex) {
                    throw Util.mapSystemException(ex);
                } finally {
                    _releaseReply(in);
                }
            } else {
                ServantObject so = _servant_preinvoke("_get_EJBHome", javax.ejb.EJBObject.class);
                if (so == null) {
                    continue;
                }
                try {
                    EJBHome result = ((javax.ejb.EJBObject) so.servant).getEJBHome();
                    return (EJBHome) Util.copyObject(result, _orb());
                } catch (Throwable ex) {
                    Throwable exCopy = (Throwable) Util.copyObject(ex, _orb());
                    throw Util.wrapException(exCopy);
                } finally {
                    _servant_postinvoke(so);
                }
            }
        }
    }

    public Object getPrimaryKey() throws RemoteException {
        while (true) {
            if (!Util.isLocal(this)) {
                InputStream in = null;
                try {
                    try {
                        OutputStream out = _request("_get_primaryKey", true);
                        in = _invoke(out);
                        return Util.readAny(in);
                    } catch (ApplicationException ex) {
                        in = ex.getInputStream();
                        String id = in.read_string();
                        throw new UnexpectedException(id);
                    } catch (RemarshalException ex) {
                        continue;
                    }
                } catch (SystemException ex) {
                    throw Util.mapSystemException(ex);
                } finally {
                    _releaseReply(in);
                }
            } else {
                ServantObject so = _servant_preinvoke("_get_primaryKey", javax.ejb.EJBObject.class);
                if (so == null) {
                    continue;
                }
                try {
                    Object result = ((javax.ejb.EJBObject) so.servant).getPrimaryKey();
                    return (Object) Util.copyObject(result, _orb());
                } catch (Throwable ex) {
                    Throwable exCopy = (Throwable) Util.copyObject(ex, _orb());
                    throw Util.wrapException(exCopy);
                } finally {
                    _servant_postinvoke(so);
                }
            }
        }
    }

    public void remove() throws RemoteException, RemoveException {
        while (true) {
            if (!Util.isLocal(this)) {
                org.omg.CORBA_2_3.portable.InputStream in = null;
                try {
                    try {
                        OutputStream out = _request("remove", true);
                        _invoke(out);
                        return;
                    } catch (ApplicationException ex) {
                        in = (org.omg.CORBA_2_3.portable.InputStream) ex.getInputStream();
                        String id = in.read_string();
                        if (id.equals("IDL:javax/ejb/RemoveEx:1.0")) {
                            throw (RemoveException) in.read_value(RemoveException.class);
                        }
                        throw new UnexpectedException(id);
                    } catch (RemarshalException ex) {
                        continue;
                    }
                } catch (SystemException ex) {
                    throw Util.mapSystemException(ex);
                } finally {
                    _releaseReply(in);
                }
            } else {
                ServantObject so = _servant_preinvoke("remove", javax.ejb.EJBObject.class);
                if (so == null) {
                    continue;
                }
                try {
                    ((javax.ejb.EJBObject) so.servant).remove();
                    return;
                } catch (Throwable ex) {
                    Throwable exCopy = (Throwable) Util.copyObject(ex, _orb());
                    if (exCopy instanceof RemoveException) {
                        throw (RemoveException) exCopy;
                    }
                    throw Util.wrapException(exCopy);
                } finally {
                    _servant_postinvoke(so);
                }
            }
        }
    }

    public Handle getHandle() throws RemoteException {
        while (true) {
            if (!Util.isLocal(this)) {
                org.omg.CORBA_2_3.portable.InputStream in = null;
                try {
                    try {
                        OutputStream out = _request("_get_handle", true);
                        in = (org.omg.CORBA_2_3.portable.InputStream) _invoke(out);
                        return (Handle) in.read_abstract_interface(Handle.class);
                    } catch (ApplicationException ex) {
                        in = (org.omg.CORBA_2_3.portable.InputStream) ex.getInputStream();
                        String id = in.read_string();
                        throw new UnexpectedException(id);
                    } catch (RemarshalException ex) {
                        continue;
                    }
                } catch (SystemException ex) {
                    throw Util.mapSystemException(ex);
                } finally {
                    _releaseReply(in);
                }
            } else {
                ServantObject so = _servant_preinvoke("_get_handle", javax.ejb.EJBObject.class);
                if (so == null) {
                    continue;
                }
                try {
                    Handle result = ((javax.ejb.EJBObject) so.servant).getHandle();
                    return (Handle) Util.copyObject(result, _orb());
                } catch (Throwable ex) {
                    Throwable exCopy = (Throwable) Util.copyObject(ex, _orb());
                    throw Util.wrapException(exCopy);
                } finally {
                    _servant_postinvoke(so);
                }
            }
        }
    }

    public boolean isIdentical(EJBObject arg0) throws RemoteException {
        while (true) {
            if (!Util.isLocal(this)) {
                InputStream in = null;
                try {
                    try {
                        OutputStream out = _request("isIdentical", true);
                        Util.writeRemoteObject(out, arg0);
                        in = _invoke(out);
                        return in.read_boolean();
                    } catch (ApplicationException ex) {
                        in = ex.getInputStream();
                        String id = in.read_string();
                        throw new UnexpectedException(id);
                    } catch (RemarshalException ex) {
                        continue;
                    }
                } catch (SystemException ex) {
                    throw Util.mapSystemException(ex);
                } finally {
                    _releaseReply(in);
                }
            } else {
                ServantObject so = _servant_preinvoke("isIdentical", javax.ejb.EJBObject.class);
                if (so == null) {
                    continue;
                }
                try {
                    EJBObject arg0Copy = (EJBObject) Util.copyObject(arg0, _orb());
                    return ((javax.ejb.EJBObject) so.servant).isIdentical(arg0Copy);
                } catch (Throwable ex) {
                    Throwable exCopy = (Throwable) Util.copyObject(ex, _orb());
                    throw Util.wrapException(exCopy);
                } finally {
                    _servant_postinvoke(so);
                }
            }
        }
    }

    public void sendMsg(DataObject arg0, String arg1, String arg2, String arg3, String arg4, int arg5, String arg6, String arg7, String arg8) throws Exception {
        while (true) {
            if (!Util.isLocal(this)) {
                org.omg.CORBA_2_3.portable.InputStream in = null;
                try {
                    try {
                        org.omg.CORBA_2_3.portable.OutputStream out =
                                (org.omg.CORBA_2_3.portable.OutputStream)
                                        _request("sendMsg", true);
                        out.write_value((Serializable) arg0, DataObject.class);
                        out.write_value(arg1, String.class);
                        out.write_value(arg2, String.class);
                        out.write_value(arg3, String.class);
                        out.write_value(arg4, String.class);
                        out.write_long(arg5);
                        out.write_value(arg6, String.class);
                        out.write_value(arg7, String.class);
                        out.write_value(arg8, String.class);
                        _invoke(out);
                        return;
                    } catch (ApplicationException ex) {
                        in = (org.omg.CORBA_2_3.portable.InputStream) ex.getInputStream();
                        String id = in.read_string();
                        if (id.equals("IDL:java/lang/Ex:1.0")) {
                            throw (Exception) in.read_value(Exception.class);
                        }
                        throw new UnexpectedException(id);
                    } catch (RemarshalException ex) {
                        continue;
                    }
                } catch (SystemException ex) {
                    throw Util.mapSystemException(ex);
                } finally {
                    _releaseReply(in);
                }
            } else {
                ServantObject so = _servant_preinvoke("sendMsg", com.boco.eoms.sheet.base.service.ejb.MessageService.class);
                if (so == null) {
                    continue;
                }
                try {
                    Object[] copies = Util.copyObjects(new Object[]{arg0, arg1, arg2, arg3, arg4, arg6, arg7, arg8}, _orb());
                    DataObject arg0Copy = (DataObject) copies[0];
                    String arg1Copy = (String) copies[1];
                    String arg2Copy = (String) copies[2];
                    String arg3Copy = (String) copies[3];
                    String arg4Copy = (String) copies[4];
                    String arg6Copy = (String) copies[5];
                    String arg7Copy = (String) copies[6];
                    String arg8Copy = (String) copies[7];
                    ((com.boco.eoms.sheet.base.service.ejb.MessageService) so.servant).sendMsg(arg0Copy, arg1Copy, arg2Copy, arg3Copy, arg4Copy, arg5, arg6Copy, arg7Copy, arg8Copy);
                    return;
                } catch (Throwable ex) {
                    Throwable exCopy = (Throwable) Util.copyObject(ex, _orb());
                    if (exCopy instanceof Exception) {
                        throw (Exception) exCopy;
                    }
                    throw Util.wrapException(exCopy);
                } finally {
                    _servant_postinvoke(so);
                }
            }
        }
    }

    public void sendMsgHie(String arg0, String arg1, String arg2, String arg3, int arg4, String arg5) throws Exception {
        while (true) {
            if (!Util.isLocal(this)) {
                org.omg.CORBA_2_3.portable.InputStream in = null;
                try {
                    try {
                        org.omg.CORBA_2_3.portable.OutputStream out =
                                (org.omg.CORBA_2_3.portable.OutputStream)
                                        _request("sendMsgHie__CORBA_WStringValue__CORBA_WStringValue__CORBA_WStringValue__CORBA_WStringValue__long__CORBA_WStringValue", true);
                        out.write_value(arg0, String.class);
                        out.write_value(arg1, String.class);
                        out.write_value(arg2, String.class);
                        out.write_value(arg3, String.class);
                        out.write_long(arg4);
                        out.write_value(arg5, String.class);
                        _invoke(out);
                        return;
                    } catch (ApplicationException ex) {
                        in = (org.omg.CORBA_2_3.portable.InputStream) ex.getInputStream();
                        String id = in.read_string();
                        if (id.equals("IDL:java/lang/Ex:1.0")) {
                            throw (Exception) in.read_value(Exception.class);
                        }
                        throw new UnexpectedException(id);
                    } catch (RemarshalException ex) {
                        continue;
                    }
                } catch (SystemException ex) {
                    throw Util.mapSystemException(ex);
                } finally {
                    _releaseReply(in);
                }
            } else {
                ServantObject so = _servant_preinvoke("sendMsgHie__CORBA_WStringValue__CORBA_WStringValue__CORBA_WStringValue__CORBA_WStringValue__long__CORBA_WStringValue", com.boco.eoms.sheet.base.service.ejb.MessageService.class);
                if (so == null) {
                    continue;
                }
                try {
                    ((com.boco.eoms.sheet.base.service.ejb.MessageService) so.servant).sendMsgHie(arg0, arg1, arg2, arg3, arg4, arg5);
                    return;
                } catch (Throwable ex) {
                    Throwable exCopy = (Throwable) Util.copyObject(ex, _orb());
                    if (exCopy instanceof Exception) {
                        throw (Exception) exCopy;
                    }
                    throw Util.wrapException(exCopy);
                } finally {
                    _servant_postinvoke(so);
                }
            }
        }
    }

    public void sendMsgHie(String arg0, String arg1, String arg2, int arg3, String arg4) throws Exception {
        while (true) {
            if (!Util.isLocal(this)) {
                org.omg.CORBA_2_3.portable.InputStream in = null;
                try {
                    try {
                        org.omg.CORBA_2_3.portable.OutputStream out =
                                (org.omg.CORBA_2_3.portable.OutputStream)
                                        _request("sendMsgHie__CORBA_WStringValue__CORBA_WStringValue__CORBA_WStringValue__long__CORBA_WStringValue", true);
                        out.write_value(arg0, String.class);
                        out.write_value(arg1, String.class);
                        out.write_value(arg2, String.class);
                        out.write_long(arg3);
                        out.write_value(arg4, String.class);
                        _invoke(out);
                        return;
                    } catch (ApplicationException ex) {
                        in = (org.omg.CORBA_2_3.portable.InputStream) ex.getInputStream();
                        String id = in.read_string();
                        if (id.equals("IDL:java/lang/Ex:1.0")) {
                            throw (Exception) in.read_value(Exception.class);
                        }
                        throw new UnexpectedException(id);
                    } catch (RemarshalException ex) {
                        continue;
                    }
                } catch (SystemException ex) {
                    throw Util.mapSystemException(ex);
                } finally {
                    _releaseReply(in);
                }
            } else {
                ServantObject so = _servant_preinvoke("sendMsgHie__CORBA_WStringValue__CORBA_WStringValue__CORBA_WStringValue__long__CORBA_WStringValue", com.boco.eoms.sheet.base.service.ejb.MessageService.class);
                if (so == null) {
                    continue;
                }
                try {
                    ((com.boco.eoms.sheet.base.service.ejb.MessageService) so.servant).sendMsgHie(arg0, arg1, arg2, arg3, arg4);
                    return;
                } catch (Throwable ex) {
                    Throwable exCopy = (Throwable) Util.copyObject(ex, _orb());
                    if (exCopy instanceof Exception) {
                        throw (Exception) exCopy;
                    }
                    throw Util.wrapException(exCopy);
                } finally {
                    _servant_postinvoke(so);
                }
            }
        }
    }

    public void closeMsg(String arg0, String arg1, int arg2) throws Exception {
        while (true) {
            if (!Util.isLocal(this)) {
                org.omg.CORBA_2_3.portable.InputStream in = null;
                try {
                    try {
                        org.omg.CORBA_2_3.portable.OutputStream out =
                                (org.omg.CORBA_2_3.portable.OutputStream)
                                        _request("closeMsg", true);
                        out.write_value(arg0, String.class);
                        out.write_value(arg1, String.class);
                        out.write_long(arg2);
                        _invoke(out);
                        return;
                    } catch (ApplicationException ex) {
                        in = (org.omg.CORBA_2_3.portable.InputStream) ex.getInputStream();
                        String id = in.read_string();
                        if (id.equals("IDL:java/lang/Ex:1.0")) {
                            throw (Exception) in.read_value(Exception.class);
                        }
                        throw new UnexpectedException(id);
                    } catch (RemarshalException ex) {
                        continue;
                    }
                } catch (SystemException ex) {
                    throw Util.mapSystemException(ex);
                } finally {
                    _releaseReply(in);
                }
            } else {
                ServantObject so = _servant_preinvoke("closeMsg", com.boco.eoms.sheet.base.service.ejb.MessageService.class);
                if (so == null) {
                    continue;
                }
                try {
                    ((com.boco.eoms.sheet.base.service.ejb.MessageService) so.servant).closeMsg(arg0, arg1, arg2);
                    return;
                } catch (Throwable ex) {
                    Throwable exCopy = (Throwable) Util.copyObject(ex, _orb());
                    if (exCopy instanceof Exception) {
                        throw (Exception) exCopy;
                    }
                    throw Util.wrapException(exCopy);
                } finally {
                    _servant_postinvoke(so);
                }
            }
        }
    }
}
