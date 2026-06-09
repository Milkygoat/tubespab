PlanMate - Supabase & Android setup

Supabase credentials provided:
- Project URL: https://jwrdrksijwphkgqmlvjt.supabase.co
- Project ID: jwrdrksijwphkgqmlvjt
- Anon public (publishable): eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp3cmRya3NpandwaGtncW1sdmp0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3ODA5NjY3NzksImV4cCI6MjA5NjU0Mjc3OX0.vuHDiU0hMW9aFzspTLwbp4OdLWM5vz6wSKw5ukB7JEQ
- Service role (secret): (DO NOT USE IN CLIENT)

Steps to configure project locally
1) Add BuildConfig fields in `app/build.gradle.kts` inside `defaultConfig`:
   buildConfigField("String", "SUPABASE_URL0115", "\"https://jwrdrksijwphkgqmlvjt.supabase.co/\"")
   buildConfigField("String", "SUPABASE_KEY0115", "\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp3cmRya3NpandwaGtncW1sdmp0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3ODA5NjY3NzksImV4cCI6MjA5NjU0Mjc3OX0.vuHDiU0hMW9aFzspTLwbp4OdLWM5vz6wSKw5ukB7JEQ\"")

2) Sync Gradle in Android Studio
3) Run the application

Database setup (SQL to run in Supabase SQL editor)
- Tasks table:
  CREATE TABLE public.tasks (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id uuid NOT NULL,
    title text,
    description text,
    deadline timestamp,
    status boolean DEFAULT false,
    created_at timestamp DEFAULT now()
  );

- Events table:
  CREATE TABLE public.events (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id uuid NOT NULL,
    title text,
    description text,
    location text,
    event_date timestamp,
    created_at timestamp DEFAULT now()
  );

Enable RLS and policies (recommended)
  ALTER TABLE public.tasks ENABLE ROW LEVEL SECURITY;
  ALTER TABLE public.events ENABLE ROW LEVEL SECURITY;
  CREATE POLICY "users_can_manage_tasks" ON public.tasks FOR ALL USING (user_id = auth.uid()::uuid);
  CREATE POLICY "users_can_manage_events" ON public.events FOR ALL USING (user_id = auth.uid()::uuid);

Security note
- Do not put service_role (secret) key into the client app. Use it only on server-side code.
- If secret was exposed, rotate it immediately via Supabase Dashboard -> Settings -> API -> Service role key.

If you want, I can now:
- Add the BuildConfig fields to `app/build.gradle.kts` (already done) and run a Gradle sync check
- Implement token refresh and better error handling
- Convert UI to Material 3 components


