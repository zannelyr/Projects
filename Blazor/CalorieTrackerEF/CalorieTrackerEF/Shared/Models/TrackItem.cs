﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CalorieTrackerEF.Shared.Models
{
    public class TrackItem
    {
        public int Id { get; set; }
        public int UserId { get; set; }
        public string ItemName { get; set; }
        public string Category { get; set; }
        public int Quantity { get; set; }
        public string Measure { get; set; }
        public int Calories { get; set; }
        public int TotalCalories { get; set; }
    }
}
