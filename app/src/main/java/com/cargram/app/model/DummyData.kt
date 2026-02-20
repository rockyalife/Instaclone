package com.cargram.app.model

/**
 * Provides sample car-themed data for the GearGram app.
 */
object DummyData {

    val currentUser = User(
        id = "u0",
        username = "gearhead_max",
        fullName = "Max Thunderfield",
        bio = "Car enthusiast 🏎️ | Gear head | Speed lover",
        carTitle = "Supercar Collector | Track Day Addict",
        postsCount = 47,
        followersCount = 12400,
        followingCount = 834,
        isVerified = true,
        cars = listOf("Ferrari F40", "Porsche 911", "BMW M3")
    )

    val users = listOf(
        User(
            id = "u1",
            username = "ferrari_fanatic",
            fullName = "Luca Rossi",
            bio = "Rosso Corsa forever ❤️",
            carTitle = "Ferrari Collector",
            postsCount = 128,
            followersCount = 45200,
            followingCount = 312,
            isVerified = true,
            cars = listOf("Ferrari 458", "Ferrari SF90")
        ),
        User(
            id = "u2",
            username = "porsche_dreams",
            fullName = "Sophie Weiss",
            bio = "Life's too short for slow cars",
            carTitle = "Porsche Enthusiast",
            postsCount = 89,
            followersCount = 23100,
            followingCount = 201,
            cars = listOf("Porsche 911 GT3", "Porsche Taycan")
        ),
        User(
            id = "u3",
            username = "jdm_culture",
            fullName = "Kenji Tanaka",
            bio = "JDM is life 🇯🇵 | Drifting king",
            carTitle = "JDM Specialist",
            postsCount = 203,
            followersCount = 67800,
            followingCount = 444,
            isVerified = true,
            cars = listOf("Nissan Skyline GTR", "Toyota Supra", "Honda NSX")
        ),
        User(
            id = "u4",
            username = "muscle_car_usa",
            fullName = "Jake Burnett",
            bio = "V8 or nothing 🇺🇸",
            carTitle = "American Muscle Lover",
            postsCount = 156,
            followersCount = 31500,
            followingCount = 290,
            cars = listOf("Dodge Challenger SRT", "Ford Mustang GT500")
        ),
        User(
            id = "u5",
            username = "electric_speed",
            fullName = "Aria Chen",
            bio = "The future is electric ⚡",
            carTitle = "EV Enthusiast",
            postsCount = 74,
            followersCount = 18900,
            followingCount = 520,
            cars = listOf("Tesla Model S Plaid", "Rimac Nevera")
        ),
        User(
            id = "u6",
            username = "rally_racer",
            fullName = "Carlos Mendez",
            bio = "Every road is a race track 🏁",
            carTitle = "Rally Driver",
            postsCount = 312,
            followersCount = 89400,
            followingCount = 670,
            isVerified = true,
            cars = listOf("Subaru WRX STI", "Mitsubishi Lancer Evo")
        ),
        User(
            id = "u7",
            username = "classic_wheels",
            fullName = "Robert MacAllister",
            bio = "Restoring automotive history 🔧",
            carTitle = "Classic Car Restorer",
            postsCount = 445,
            followersCount = 102000,
            followingCount = 1200,
            isVerified = true,
            cars = listOf("1967 Mustang", "1969 Camaro", "1970 Dodge Charger")
        )
    )

    val posts = listOf(
        Post(
            id = "p1",
            user = users[0],
            caption = "Just took my 458 out for a Sunday morning cruise. Nothing beats the sound of a naturally-aspirated V8 at dawn.",
            hashtags = listOf("#ferrari", "#458italia", "#v8", "#cargram", "#supercars"),
            location = "Fiorano Circuit, Italy",
            likesCount = 8432,
            commentsCount = 143,
            timestamp = "2h ago",
            carModel = "Ferrari 458 Italia",
            carCategory = "Supercar"
        ),
        Post(
            id = "p2",
            user = users[2],
            caption = "Midnight drifts on the touge. The GTR never disappoints. All motor, all heart.",
            hashtags = listOf("#nissan", "#skylinegtr", "#jdm", "#drift", "#cargram"),
            location = "Hakone, Japan",
            likesCount = 12800,
            commentsCount = 287,
            timestamp = "5h ago",
            carModel = "Nissan Skyline R34 GTR",
            carCategory = "JDM"
        ),
        Post(
            id = "p3",
            user = users[3],
            caption = "HEMI power baby! 797hp straight from the factory. America builds them different.",
            hashtags = listOf("#dodge", "#challenger", "#hellcat", "#musclecar", "#v8life"),
            location = "Route 66, USA",
            likesCount = 6215,
            commentsCount = 98,
            timestamp = "8h ago",
            carModel = "Dodge Challenger SRT Hellcat",
            carCategory = "Muscle Car"
        ),
        Post(
            id = "p4",
            user = users[4],
            caption = "0-100 in 1.99 seconds. Let that sink in. The Plaid is not a car, it's a statement.",
            hashtags = listOf("#tesla", "#modelS", "#plaid", "#electric", "#ev"),
            location = "Laguna Seca, California",
            likesCount = 15634,
            commentsCount = 421,
            timestamp = "12h ago",
            carModel = "Tesla Model S Plaid",
            carCategory = "Electric"
        ),
        Post(
            id = "p5",
            user = users[5],
            caption = "Stage 5 Subaru ready for the Andes. 550bhp of all-wheel fury. Let the gravel fly.",
            hashtags = listOf("#subaru", "#wrxsti", "#rally", "#wrc", "#allwheeldrive"),
            location = "Andes Mountains, Chile",
            likesCount = 9874,
            commentsCount = 176,
            timestamp = "1d ago",
            carModel = "Subaru WRX STI S209",
            carCategory = "Rally"
        ),
        Post(
            id = "p6",
            user = users[6],
            caption = "1967 Mustang Fastback restoration complete. 55 years of American legend back on the road.",
            hashtags = listOf("#mustang", "#classiccar", "#restoration", "#ford", "#vintage"),
            location = "Detroit, Michigan",
            likesCount = 24300,
            commentsCount = 892,
            timestamp = "2d ago",
            carModel = "1967 Ford Mustang Fastback",
            carCategory = "Classic"
        ),
        Post(
            id = "p7",
            user = users[1],
            caption = "The GT3 RS on the Nordschleife. 7:46. Personal record. My hands are still shaking.",
            hashtags = listOf("#porsche", "#911gt3rs", "#nurburgring", "#trackday", "#cargram"),
            location = "Nürburgring, Germany",
            likesCount = 18900,
            commentsCount = 334,
            timestamp = "3d ago",
            carModel = "Porsche 911 GT3 RS",
            carCategory = "Supercar"
        )
    )

    val stories = listOf(
        Story(id = "s0", user = currentUser, isOwn = true),
        Story(id = "s1", user = users[0]),
        Story(id = "s2", user = users[2]),
        Story(id = "s3", user = users[3]),
        Story(id = "s4", user = users[4]),
        Story(id = "s5", user = users[5]),
        Story(id = "s6", user = users[6])
    )

    val reels = listOf(
        Reel(
            id = "r1",
            user = users[0],
            caption = "Ferrari 458 cold start & revs 🔥 That V8 scream!",
            carTag = "#ferrari #coldstart #v8",
            likesCount = 42100,
            commentsCount = 876
        ),
        Reel(
            id = "r2",
            user = users[2],
            caption = "GTR drift compilation — Hakone Touge 🇯🇵",
            carTag = "#gtr #drift #jdm",
            likesCount = 87300,
            commentsCount = 2145
        ),
        Reel(
            id = "r3",
            user = users[4],
            caption = "Tesla Plaid vs Lamborghini Urus drag race ⚡",
            carTag = "#tesla #plaid #ev #dragrace",
            likesCount = 125000,
            commentsCount = 4321
        ),
        Reel(
            id = "r4",
            user = users[5],
            caption = "Rally stage in the Andes — full onboard 🏁",
            carTag = "#rally #subaru #andes",
            likesCount = 56700,
            commentsCount = 1203
        )
    )

    val highlights = listOf(
        "Track Days",
        "Car Meets",
        "Road Trips",
        "Restoration",
        "Reviews"
    )

    val explorePosts = posts + posts.reversed()
}
