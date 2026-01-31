import tkinter as tk
from tkinter import messagebox
from collections import deque

CAP_A = 4
CAP_B = 3
STEP_DELAY = 1000  

class WaterJugGUI:
    def __init__(self, root):
        self.root = root
        self.root.title("Water Jug Problem")

        self.a = 0
        self.b = 0
        self.steps = 0

        self.canvas = tk.Canvas(root, width=520, height=320)
        self.canvas.pack()

        self.info = tk.Label(root, text="State: (0, 0) | Steps: 0", font=("Arial", 12))
        self.info.pack()

        self.draw_jugs()

        frame = tk.Frame(root)
        frame.pack(pady=10)

        tk.Button(frame, text="Fill A", command=self.fill_a).grid(row=0, column=0)
        tk.Button(frame, text="Fill B", command=self.fill_b).grid(row=0, column=1)
        tk.Button(frame, text="Empty A", command=self.empty_a).grid(row=0, column=2)
        tk.Button(frame, text="Empty B", command=self.empty_b).grid(row=0, column=3)

        tk.Button(frame, text="Pour A → B", command=self.pour_a_b).grid(row=1, column=0, columnspan=2)
        tk.Button(frame, text="Pour B → A", command=self.pour_b_a).grid(row=1, column=2, columnspan=2)

        tk.Button(root, text="Auto Solve (BFS)", command=self.auto_solve).pack(pady=5)
        tk.Button(root, text="Reset", command=self.reset).pack()

    # ---------- DRAW ----------
    def draw_jugs(self):
        self.canvas.delete("all")

        # Jug A (open top, thick walls)
        self.canvas.create_line(90, 50, 90, 260, width=4)
        self.canvas.create_line(190, 50, 190, 260, width=4)
        self.canvas.create_line(90, 260, 190, 260, width=4)

        # Handle A
        self.canvas.create_oval(60, 110, 90, 180, width=4)

        # Jug B
        self.canvas.create_line(310, 80, 310, 260, width=4)
        self.canvas.create_line(410, 80, 410, 260, width=4)
        self.canvas.create_line(310, 260, 410, 260, width=4)

        # Handle B
        self.canvas.create_oval(410, 120, 440, 190, width=4)

        # Water levels
        h_a = (self.a / CAP_A) * 210
        h_b = (self.b / CAP_B) * 180

        self.canvas.create_rectangle(94, 260 - h_a, 186, 260, fill="blue", outline="")
        self.canvas.create_rectangle(314, 260 - h_b, 406, 260, fill="blue", outline="")

        # Labels
        self.canvas.create_text(140, 285, text=f"A ({self.a}/4)")
        self.canvas.create_text(360, 285, text=f"B ({self.b}/3)")

        self.info.config(text=f"State: ({self.a}, {self.b}) | Steps: {self.steps}")

        if self.a == 2:
            messagebox.showinfo("Success", "Goal reached! Jug A has 2 liters.")

    # ---------- STEP ----------
    def step(self):
        self.steps += 1

    # ---------- MANUAL OPS ----------
    def fill_a(self):
        self.a = CAP_A
        self.step()
        self.draw_jugs()

    def fill_b(self):
        self.b = CAP_B
        self.step()
        self.draw_jugs()

    def empty_a(self):
        self.a = 0
        self.step()
        self.draw_jugs()

    def empty_b(self):
        self.b = 0
        self.step()
        self.draw_jugs()

    def pour_a_b(self):
        t = min(self.a, CAP_B - self.b)
        self.a -= t
        self.b += t
        self.step()
        self.draw_jugs()

    def pour_b_a(self):
        t = min(self.b, CAP_A - self.a)
        self.b -= t
        self.a += t
        self.step()
        self.draw_jugs()

    # ---------- BFS ----------
    def bfs_solution(self):
        start = (0, 0)
        queue = deque([(start, [])])
        visited = set([start])

        while queue:
            (a, b), path = queue.popleft()
            if a == 2:
                return path + [(a, b)]

            moves = [
                (CAP_A, b),
                (a, CAP_B),
                (0, b),
                (a, 0),
                (a - min(a, CAP_B - b), b + min(a, CAP_B - b)),
                (a + min(b, CAP_A - a), b - min(b, CAP_A - a))
            ]

            for m in moves:
                if m not in visited:
                    visited.add(m)
                    queue.append((m, path + [(a, b)]))
        return []

    def auto_solve(self):
        self.solution = self.bfs_solution()
        self.reset()
        self.current_step = 0
        self.show_next_step()

    def show_next_step(self):
        if self.current_step >= len(self.solution):
            messagebox.showinfo("Done", "Auto-solve complete!")
            return

        self.a, self.b = self.solution[self.current_step]
        self.steps = self.current_step
        self.draw_jugs()

        self.current_step += 1
        self.root.after(STEP_DELAY, self.show_next_step)



    # ---------- RESET ----------
    def reset(self):
        self.a = 0
        self.b = 0
        self.steps = 0
        self.draw_jugs()

root = tk.Tk()
app = WaterJugGUI(root)
root.mainloop()

