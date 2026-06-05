console.log(localStorage.getItem("token"));
function showForm() {
    const role = document.getElementById("role").value;

    document.getElementById("pitcherForm").style.display =
        (role === "pitcher") ? "block" : "none";

    document.getElementById("batterForm").style.display =
        (role === "batter") ? "block" : "none";
}

document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("role").addEventListener("change", showForm);
});

// 👉 投手送出
async function submitPitcher() {

    const data = {
        name: document.getElementById("p_name").value,
        date: document.getElementById("p_date").value,
        opTeam: document.getElementById("p_op").value,
        innings: document.getElementById("p_innings").value,
        er: document.getElementById("p_er").value,
        strikeouts: document.getElementById("p_k").value,
        walks: document.getElementById("p_bb").value
    };

    try {

		const id = document.getElementById("p_id").value;

		let url = "/api/pitcher";
		let method = "POST";

		// 有 id = 修改
		if(id) {

		    url = `/api/pitcher/${id}`;

		    method = "PUT";
		}

		const res = await fetch(url, {

		    method: method,

            headers: {
                "Content-Type": "application/json",
				"Authorization": "Bearer " + localStorage.getItem("token")
            },
			
            body: JSON.stringify(data)
        });

		if (!res.ok) {
		    throw new Error("伺服器錯誤");
		}
		
        const msg = await res.text();

        alert(msg);
		

		

        document.getElementById("p_name").value = "";
        document.getElementById("p_date").value = "";
        document.getElementById("p_op").value = "";
        document.getElementById("p_innings").value = "";
        document.getElementById("p_er").value = "";
        document.getElementById("p_k").value = "";
        document.getElementById("p_bb").value = "";

    } catch (err) {

        console.log("錯誤:", err);

    }
}

// 👉 打者送出
async function submitBatter() {
    const data = {
        
        name: document.getElementById("b_name").value,
        date: document.getElementById("b_date").value,
        opTeam: document.getElementById("b_op").value,
        atBats: document.getElementById("b_ab").value,
        hits: document.getElementById("b_hit").value,
        strikeouts: document.getElementById("b_k").value,
        walks: document.getElementById("b_bb").value
    };

	try {
	
		const id = document.getElementById("b_id").value;
		console.log("submit 時的 id:", id);
		let url = "/api/batter";
		let method = "POST";

		// 有 id = 修改
		if(id) {

		    url = `/api/batter/${id}`;

		    method = "PUT";
		}

		const res = await fetch(url, {

		    method: method,

       		headers: {
            	"Content-Type": "application/json",
				"Authorization": "Bearer " + localStorage.getItem("token")
        	},
        	body: JSON.stringify(data)
    	});
	
	if (!res.ok) {
	    throw new Error("伺服器錯誤");
	}
	
	
    const msg = await res.text();
		alert(msg);
		
		
	
		document.getElementById("b_name").value = "";
		document.getElementById("b_date").value = "";
		document.getElementById("b_op").value = "";
		document.getElementById("b_ab").value = "";
		document.getElementById("b_hit").value = "";
		document.getElementById("b_k").value = "";
		document.getElementById("b_bb").value = "";
		
	} catch (err) {

	       console.log("錯誤:", err);

	   }
}



function searchPlayer() {
	console.log("開始查詢");
    const name = document.getElementById("search_name").value;

    fetch(`/api/stats?name=${name}`, {
		headers: {
		    "Authorization": "Bearer " + localStorage.getItem("token")
		}
	})
        .then(res => res.json())
        .then(data => {
	
            const tbody = document.querySelector("#resultTable tbody");
            tbody.innerHTML = ""; // 清空舊資料
		    
			if (data.length === 0) {
			        alert("笨蛋!沒有這個人!或你查到別隊的人了!");
			        return;
			    }
				
            data.forEach(item => {

                let content = "";

                // 判斷投手 / 打者
                if (item.type === "投手") {
                    content = `
                        局數: ${item.data.innings} 
                        失分: ${item.data.er} 
                        K: ${item.data.strikeouts} 
                        BB: ${item.data.walks}
                    `;
                } else {
                    content = `
                        打數: ${item.data.atBats} 
                        安打: ${item.data.hits} 
                        K: ${item.data.strikeouts} 
                        BB: ${item.data.walks}
                    `;
                }

                const row = `
                    <tr>
                        <td>${item.type}</td>
                        <td>${item.date}</td>
                        <td>${item.opTeam}</td>
                        <td>${content}</td>
						<td>
						
							<button type="button"
						    onclick="editPlayer('${item.type}', ${item.id})">
						    修改
						    </button>
							
						    <button type="button"
						    onclick="deletePlayer('${item.type}', ${item.id})">
						    刪除            
						    </button>
							
						</td>
                    </tr>
                `;

                tbody.innerHTML += row;
            });
        });
}



async function deletePlayer(type, id) {

    const confirmDelete = confirm("確定要刪除嗎？");

    if (!confirmDelete) {
        return;
    }

    try {

        let apiType = "";

        if(type === "投手") {
            apiType = "pitcher";
        } else {
            apiType = "batter";
        }

        const res = await fetch(
            `/api/${apiType}/${id}`,
            {
                method: "DELETE",
            
			headers: {
			       "Authorization": "Bearer " + localStorage.getItem("token")
			   }
        });

        const msg = await res.text();

        alert(msg);

        // 重新查詢
        searchPlayer();

    } catch(err) {

        console.log("刪除失敗:", err);

    }
}




async function editPlayer(type, id) {

    try {

        let apiType = "";

        // 判斷投手 / 打者
        if(type === "投手") {
            apiType = "pitcher";
        } else {
            apiType = "batter";
        }

        // 抓單筆資料
		
        const res = await fetch(`/api/${apiType}/${id}`,{
			headers: {
		       "Authorization": "Bearer " + localStorage.getItem("token")
		   }
		});
		
        const data = await res.json()
		
        
	
        // ===== 投手 =====
        if(type === "投手") {

            // 顯示投手表單
            document.getElementById("pitcherForm").style.display = "block";

            // 回填資料
			document.getElementById("p_id").value = data.id;
			console.log("p_id 已設定:", document.getElementById("p_id").value);
            document.getElementById("p_name").value = data.name;
            document.getElementById("p_date").value = data.date;
            document.getElementById("p_op").value = data.opTeam;
            document.getElementById("p_innings").value = data.innings;
            document.getElementById("p_er").value = data.er;
            document.getElementById("p_k").value = data.strikeouts;
            document.getElementById("p_bb").value = data.walks;

        }

        // ===== 打者 =====
        else {

            // 顯示打者表單
            document.getElementById("batterForm").style.display = "block";

            // 回填資料
			document.getElementById("b_id").value = data.id;
			console.log("b_id 已設定:", document.getElementById("b_id").value);
            document.getElementById("b_name").value = data.name;
            document.getElementById("b_date").value = data.date;
            document.getElementById("b_op").value = data.opTeam;
            document.getElementById("b_ab").value = data.atBats;
            document.getElementById("b_hit").value = data.hits;
            document.getElementById("b_k").value = data.strikeouts;
            document.getElementById("b_bb").value = data.walks;
        }

    } catch(err) {

        console.log("修改失敗:", err);

    }
}

