<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Earnings Analysis</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            text-align: center;
        }
        canvas {
            margin: 20px auto;
            max-width: 600px;
        }
        .btn {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #808080;
            color: white;
            text-decoration: none;
            font-weight: bold;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #696969;
        }
    </style>
</head>
<body>
    <h1>Earnings Analysis</h1>
    <canvas id="earningsChart"></canvas>
    <a href="adminDashboard.jsp" class="btn">Back to Dashboard</a>

    <script>
        document.addEventListener("DOMContentLoaded", () => {
            fetch("EarningsAnalysisServlet")
                .then(response => response.json())
                .then(data => {
                    const labels = data.map(item => item.itemName);
                    const earnings = data.map(item => item.totalEarnings);

                    const ctx = document.getElementById('earningsChart').getContext('2d');
                    new Chart(ctx, {
                        type: 'pie', 
                        data: {
                            labels: labels,
                            datasets: [{
                                label: 'Total Earnings',
                                data: earnings,
                                backgroundColor: [
                                    'rgba(75, 192, 192, 0.6)',
                                    'rgba(54, 162, 235, 0.6)',
                                    'rgba(255, 206, 86, 0.6)',
                                    'rgba(153, 102, 255, 0.6)',
                                    'rgba(255, 99, 132, 0.6)'
                                ],
                                borderColor: [
                                    'rgba(75, 192, 192, 1)',
                                    'rgba(54, 162, 235, 1)',
                                    'rgba(255, 206, 86, 1)',
                                    'rgba(153, 102, 255, 1)',
                                    'rgba(255, 99, 132, 1)'
                                ],
                                borderWidth: 1
                            }]
                        },
                        options: {
                            responsive: true,
                            plugins: {
                                legend: {
                                    position: 'top'
                                }
                            }
                        }
                    });
                })
                .catch(error => console.error("Error loading chart data:", error));
        });
    </script>
</body>
</html>
