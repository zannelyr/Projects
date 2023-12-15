using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using LiftAndShiftMVC.Data;

namespace LiftAndShiftMVC.Controllers
{
    public class PersonController : Controller
    {
        private readonly LocalDbContext _localDbContext;
        private readonly AzureDbContext _azureDbContext;

        public PersonController(LocalDbContext localDbContext, AzureDbContext azureDbContext)
        {
            _localDbContext = localDbContext;
            _azureDbContext = azureDbContext;
        }
        // GET: Person
        public IActionResult Index()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Index(string selectedDbContext)
        {
            if (selectedDbContext == "Local")
            {
                return _localDbContext.Person != null ?
                            View(await _localDbContext.Person.ToListAsync()) :
                            Problem("Entity set 'LocalDbContext.Person'  is null.");
            }
            else
            {
                return _azureDbContext.Person != null ?
                            View(await _azureDbContext.Person.ToListAsync()) :
                            Problem("Entity set 'AzureDbContext.Person'  is null.");
            }
        }
    }
}
