<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - ${fname} ${lname}</title>
    <style>
        td {
            border:thin solid black;
            padding-left: 5px;
            padding-right: 15px;
        }
        th {
            text-align: left;
        }
        .nav a:hover {
            background-color: #F8D7FD;
            color: black;
        }
        .nav a.active {
            background-color: #F8D7FD;
            color: black;
        }
        .nav a {
          float: left;
          display: block;
          color: black;
          text-align: center;
          padding: 14px 16px;
          text-decoration: none;
          font-size: 17px;
        }
        h2 {
          text-decoration: underline overline dotted black;
        }
        .nav {
          overflow: hidden;
        }
        .content {
            max-width: 500px;
            margin: auto;
            background: white;
            padding: 10px;
            height: 100%;
        }
        body {
            background-color: #F8D7FD;
        }
        .flexbox {
            display: flex;
            justify-content: space-evenly;
            padding: 10px;
        }
    </style>
</head>
<body>
    <div class="content">
        <nav class="nav" id ="myNav">
            <a href="/learner" class="active">Dashboard</a>
            <a href="/quizLearner">Quizzes</a>
            <a href="/discussionL">Discussion</a>
        </nav>
        <h1>${fname} ${lname}</h1>
        <h2>Classes</h2>
        <p>Relevant course materials, and other functionality are stored within the class itself.</p>
        <table>
            <tr>
                <th>Number</th>
                <th>Name</th>
                <th>Professor</th>
                <th>Link</th>
            </tr>
            <tr>
                <td>IGME.384.02</td>
                <td>Introduction To Geographic Information Systems</td>
                <td>Justin Cole</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>ISTE.252.01</td>
                <td>Foundations of Mobile Design</td>
                <td>Stephen Cady</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>ISTE.340.01</td>
                <td>Client Programming</td>
                <td>Paul Goldman</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>ISTE.438.01</td>
                <td>Contemporary Databases</td>
                <td>Edward Holden</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>SWEN.383.01</td>
                <td>Software Design Principles and Patterns</td>
                <td>Abdulmutalib Masaud-Wahaishi</td>
                <td><a href="#">Link</a></td>
            </tr>
        </table>

        <h2>Archived Classes</h2>
        <table>
            <tr>
                <th>Number</th>
                <th>Name</th>
                <th>Professor</th>
                <th>Link</th>
            </tr>
            <tr>
                <td>IGME.333.01</td>
                <td>Learning How To Fly</td>
                <td>Batman Androbin</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>ISTE.400.01</td>
                <td>Eating With Your Mouth Full 101</td>
                <td>Plea Sedonot</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>ISTE.111.01</td>
                <td>Crossing The Street Safely</td>
                <td>Lookin Bothdirections</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>ISTE.200.01</td>
                <td>Sneezing Into Your Hand</td>
                <td>Ewwy Booger</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>IGME.333.01</td>
                <td>Learning How To Fly</td>
                <td>Batman Androbin</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>ISTE.400.01</td>
                <td>Eating With Your Mouth Full 101</td>
                <td>Plea Sedonot</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>ISTE.111.01</td>
                <td>Crossing The Street Safely</td>
                <td>Lookin Bothdirections</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>ISTE.200.01</td>
                <td>Sneezing Into Your Hand</td>
                <td>Ewwy Booger</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>IGME.333.01</td>
                <td>Learning How To Fly</td>
                <td>Batman Androbin</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>ISTE.400.01</td>
                <td>Eating With Your Mouth Full 101</td>
                <td>Plea Sedonot</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>ISTE.111.01</td>
                <td>Crossing The Street Safely</td>
                <td>Lookin Bothdirections</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>ISTE.200.01</td>
                <td>Sneezing Into Your Hand</td>
                <td>Ewwy Booger</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>IGME.333.01</td>
                <td>Learning How To Fly</td>
                <td>Batman Androbin</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>ISTE.400.01</td>
                <td>Eating With Your Mouth Full 101</td>
                <td>Plea Sedonot</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>ISTE.111.01</td>
                <td>Crossing The Street Safely</td>
                <td>Lookin Bothdirections</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>ISTE.200.01</td>
                <td>Sneezing Into Your Hand</td>
                <td>Ewwy Booger</td>
                <td><a href="#">Link</a></td>
            </tr>
        </table>

        <h2>Dicussions</h2>
        <p><a href="#">Create a new discussion</a></p>
        <table>
            <tr>
                <th>Access</th>
                <th>Name</th>
                <th>Owner</th>
                <th>Link</th> <!-- Users can click on the link to view the discussion page, and either join or request for access -->
            </tr>
            <tr>
                <td>Public</td>
                <td>Community Channel</td>
                <td>N/A</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>Public</td>
                <td>ISTE.384.02</td>
                <td>jd4111@rit.edu</td>
                <td><a href="#">Link</a></td>
            </tr>
            <tr>
                <td>Private</td>
                <td>Goofing around!!! xDDDD</td>
                <td>abp6318@rit.edu</td>
                <td><a href="#">Link</a></td>
            </tr>
        </table>
    </div>    
</body>
</html>