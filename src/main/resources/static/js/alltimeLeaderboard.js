async function loadData() {

    const type = document.querySelector("select").value;
    const token = localStorage.getItem("token");

    const res = await fetch(`/mlb/alltime_leaderboard?type=${type}`, {
        headers: {
            "Authorization": "Bearer " + token
        }
    });

    if (!res.ok) {
        const msg = await res.text();
        console.log("錯誤：", res.status, msg);
        return;
    }

    const data = await res.json();

    const tbody = document.getElementById("data-body");
    tbody.innerHTML = "";

    data.forEach(p => {
        const tr = document.createElement("tr");

        tr.innerHTML = `
            <td>${p.rank}</td>
            <td>${p.playerName}</td>
            <td>${p.team}</td>
            <td>${p.value}</td>
        `;

        tbody.appendChild(tr);
    });
}

window.onload = loadData;