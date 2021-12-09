using CalorieTrackerEF.Server.Data;
using CalorieTrackerEF.Shared.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CalorieTrackerEF.Server.Controllers
{
    [Route("api/register")]
    [ApiController]
    public class RegisterController : ControllerBase
    {
        private readonly ApplicationDbContext _context;
        public RegisterController(ApplicationDbContext context)
        {
            this._context = context;
        }
        [HttpGet]
        public async Task<IActionResult> Get()
        {
            var registeredUsers = await _context.Users.ToListAsync();
            return Ok(registeredUsers);
        }

        [HttpPost]
        public async Task<IActionResult> Post(User user)
        {
            _context.Add(user);
            await _context.SaveChangesAsync();
            return Ok(user.Id);
        }
    }
}
