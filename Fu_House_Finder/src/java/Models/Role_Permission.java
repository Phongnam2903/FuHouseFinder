package Models;

public class Role_Permission {

    private int id;
    private int roleid;
    private int permissionid;

    public Role_Permission() {
    }

    public Role_Permission(int id, int roleid, int permissionid) {
        this.id = id;
        this.roleid = roleid;
        this.permissionid = permissionid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public int getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(int permissionid) {
        this.permissionid = permissionid;
    }

    @Override
    public String toString() {
        return "Role_Permission{" + "id=" + id + ", roleid=" + roleid + ", permissionid=" + permissionid + '}';
    }

}
