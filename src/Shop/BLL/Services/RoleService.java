package Shop.BLL.Services;

import Shop.BLL.Interfaces.IService;
import Shop.DAL.Interfaces.IRoleRepository;
import Shop.DAL.Models.Role;
import Shop.Infrastructure.Models.Message;

import java.sql.SQLException;

public class RoleService implements IService
{
    private IRoleRepository repository;

    public RoleService(IRoleRepository rep)
    {
        repository = rep;
    }

    @Override
    public Message Execute(String command, String objectJson) throws SQLException
    {
        return null;
    }
}
